package ru.gitcoder.telegram.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.gitcoder.telegram.api.payload.request.TelegramRequest;
import ru.gitcoder.telegram.api.payload.request.text.TextRequest;
import ru.gitcoder.telegram.api.payload.request.text.TextOptions;
import ru.gitcoder.telegram.api.exception.TelegramNetworkException;
import ru.gitcoder.telegram.api.exception.TelegramResponseException;
import ru.gitcoder.telegram.api.payload.response.TelegramResponse;
import ru.gitcoder.telegram.api.utility.HttpUtil;
import ru.gitcoder.telegram.api.utility.JsonUtil;
import ru.gitcoder.telegram.api.utility.UrlUtil;
import ru.gitcoder.telegram.api.validator.TelegramApiValidator;

@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("UnusedReturnValue")
public final class TelegramApiService {
    private final RestTemplate restTemplate;
    private final String accessToken;

    public TelegramResponse sendMessage(@NotNull Long chatId, @NotNull String text, @NotNull TextOptions options) {
        return execute(TextRequest.builder()
                .text(text)
                .options(options)
                .chatId(chatId)
                .build());
    }

    public TelegramResponse sendMessage(@NotNull TextRequest request) {
        return execute(request);
    }

    public TelegramResponse sendMessage(@NotNull Long chatId, @NotNull String text) {
        return execute(TextRequest.builder()
                .text(text)
                .chatId(chatId)
                .build());
    }

    private <T extends TelegramResponse> T execute(@NotNull TelegramRequest<?> request, @NotNull Class<T> responseClass) {
        String serializedClass = JsonUtil.toJson(request);
        HttpEntity<String> httpEntity = HttpUtil.createHttpEntity(serializedClass);

        String requestAction = request.getAction();
        String requestUrl = UrlUtil.buildApiMethodUrl(accessToken, requestAction);

        log.debug("Sending Telegram API request to {} with payload: {}", requestUrl, serializedClass);
        ResponseEntity<T> responseEntity;
        try {
            responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, httpEntity, responseClass);
        } catch (HttpStatusCodeException e) {
            HttpStatusCode statusCode = e.getStatusCode();
            log.error("HTTP error while executing Telegram API request to {}: {} {}",
                    requestUrl, statusCode, e.getMessage(), e);
            throw new TelegramNetworkException("HTTP error connecting to Telegram API: " + e.getStatusText(), e, statusCode);
        } catch (RestClientException e) {
            log.error("Unexpected error while executing Telegram API request to {}: {}",
                    requestUrl, e.getMessage(), e);
            throw new TelegramNetworkException("Unexpected error connecting to Telegram API", e, HttpStatusCode.valueOf(503));
        }

        try {
            TelegramApiValidator.validateResponse(responseEntity);
        } catch (TelegramResponseException e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        T response = responseEntity.getBody();
        log.debug("Received response: {}", response);
        return response;
    }

    private TelegramResponse execute(@NotNull TelegramRequest<?> dto) {
        return execute(dto, TelegramResponse.class);
    }
}
