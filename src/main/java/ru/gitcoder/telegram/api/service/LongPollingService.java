package ru.gitcoder.telegram.api.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.gitcoder.telegram.api.configuration.LongPollingConfig;
import ru.gitcoder.telegram.api.exception.TelegramApiException;
import ru.gitcoder.telegram.api.exception.TelegramNetworkException;
import ru.gitcoder.telegram.api.update.polling.LongPollingExecutor;
import ru.gitcoder.telegram.api.metrics.MetricsFacade;
import ru.gitcoder.telegram.api.model.message.Message;
import ru.gitcoder.telegram.api.payload.response.GetUpdateResponse;
import ru.gitcoder.telegram.api.payload.response.update.PhotoMessage;
import ru.gitcoder.telegram.api.payload.response.update.TextMessage;
import ru.gitcoder.telegram.api.payload.response.update.Update;
import ru.gitcoder.telegram.api.update.UpdateHandler;
import ru.gitcoder.telegram.api.utility.TokenUtil;
import ru.gitcoder.telegram.api.utility.UrlUtil;
import ru.gitcoder.telegram.api.validator.TelegramApiValidator;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public final class LongPollingService {
    private final LongPollingExecutor executor = new LongPollingExecutor();

    @Getter
    private final LongPollingConfig config;

    private final RestTemplate restTemplate;
    private final UpdateHandler updateHandler;

    private final MetricsFacade metrics;

    @Getter
    private long lastProcessedOffset = 0L;

    public void init(@NotNull String accessToken) {
        if (isRunning()) {
            log.warn("Long polling is already started");
            return;
        }

        log.debug("Initiating long polling for bot with token: {}", TokenUtil.mask(accessToken));
        start(accessToken);
        log.debug("Started long polling for bot");
    }

    public void resetOffset(long newOffset) {
        if (newOffset < 0) {
            throw new IllegalArgumentException("Offset cannot be negative: " + newOffset);
        }
        lastProcessedOffset = newOffset;
        log.debug("Offset reset to: {}", newOffset);
    }

    public void pause(long millis) {
        executor.pause(millis);
    }

    public boolean isRunning() {
        return executor.isRunning();
    }

    public void resume() {
        executor.resume();
    }

    public void shutdown() {
        executor.shutdown();
    }

    private void start(@NotNull String accessToken) {
        executor.start(() -> {
            while (isRunning()) {
                try {
                    doRequestUpdate(accessToken);
                } catch (TelegramNetworkException e) {
                    log.error("Network error during long polling process", e);
                    executor.retry();
                }
            }
        });
    }

    @SuppressWarnings("DataFlowIssue")
    private void doRequestUpdate(@NotNull String accessToken) {
        String requestUrl = UrlUtil.buildGetUpdatesUrl(accessToken, lastProcessedOffset, config.getPollingTimeout());
        try {
            ResponseEntity<GetUpdateResponse> responseEntity = restTemplate.getForEntity(requestUrl, GetUpdateResponse.class);
            TelegramApiValidator.validateResponse(responseEntity);

            GetUpdateResponse response = responseEntity.getBody();
            handleResponse(response);
        } catch (HttpStatusCodeException e) {
            HttpStatusCode statusCode = e.getStatusCode();
            log.error("HTTP error while executing Telegram API request to {}: {} {}", requestUrl, statusCode, e.getMessage(), e);
            throw new TelegramNetworkException("HTTP error connecting to Telegram API: " + e.getStatusText(), e, statusCode);
        } catch (RestClientException e) {
            if (e.getCause() instanceof JsonMappingException) {
                log.error("Failed to deserialize Telegram API response from {}: {}", requestUrl, e.getMessage(), e);
                throw new TelegramNetworkException("Invalid response format from Telegram API", e, HttpStatusCode.valueOf(500));
            }
            log.error("Unexpected error while executing Telegram API request to {}: {}", requestUrl, e.getMessage(), e);
            throw new TelegramNetworkException("Unexpected error connecting to Telegram API", e, HttpStatusCode.valueOf(500));
        }
    }

    private void handleResponse(@NotNull GetUpdateResponse response) {
        List<Update> updates = response.getUpdates();
        if (updates == null || updates.isEmpty()) {
            return;
        }
        for (Update update : updates) {
            handleUpdate(update);
        }
    }

    private void handleUpdate(@NotNull Update update) {
        Instant start = Instant.now();
        try {
            TelegramApiValidator.validateUpdate(update);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            throw e;
        }
        try {
            Message message = update.getMessage();
            handleMessage(message);

            Long updateId = update.getUpdateId();
            incrementOffset(updateId);
        } catch (Exception exception) {
            log.error("Failed to process update with ID {}: {}", update.getUpdateId(), exception.getMessage(), exception);
            throw exception;
        } finally {
            if (metrics != null && metrics.isEnabled()) {
                recordMetrics(update, start);
            }
        }
    }

    private void handleMessage(@NotNull Message message) {
        if (message instanceof TextMessage) {
            updateHandler.handle((TextMessage) message);
            return;
        }
        if (message instanceof PhotoMessage) {
            updateHandler.handle((PhotoMessage) message);
            return;
        }

        throw new TelegramApiException("Handle failed, unsupported message type: " + message.getClass().getSimpleName());
    }

    private void recordMetrics(Update update, Instant start) {
        Instant end = Instant.now();
        double processingTimeMs = Duration.between(start, end).toMillis();
        metrics.recordUpdate(update, processingTimeMs);
    }

    private void incrementOffset(long updateId) {
        lastProcessedOffset = updateId + 1;
    }
}
