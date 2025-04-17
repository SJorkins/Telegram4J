package ru.gitcoder.telegram.api.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gitcoder.telegram.api.exception.TelegramApiException;
import ru.gitcoder.telegram.api.exception.TelegramNetworkException;
import ru.gitcoder.telegram.api.exception.TelegramResponseException;
import ru.gitcoder.telegram.api.model.message.Message;
import ru.gitcoder.telegram.api.payload.response.update.Update;
import ru.gitcoder.telegram.api.payload.response.TelegramResponse;

import java.util.concurrent.TimeUnit;

@Slf4j
@UtilityClass
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TelegramApiValidator {

    public void validateResponse(ResponseEntity<? extends TelegramResponse> responseEntity) {
        TelegramResponse response = responseEntity.getBody();
        if (response == null) {
            throw new TelegramNetworkException("Empty or invalid response from Telegram API", responseEntity.getStatusCode());
        }

        HttpStatus httpStatus = (HttpStatus) responseEntity.getStatusCode();
        validateResponseStatus(httpStatus, response);
    }

    public void validateUpdate(Update update) {
        Long updateId = update.getUpdateId();
        Message message = update.getMessage();

        if (updateId <= 0) {
            throw new TelegramApiException("An negative update id was given");
        }
        if (message == null) {
            throw new TelegramApiException("An empty update has received");
        }
    }

    public void validateMetric(long recordInterval, TimeUnit recordIntervalTimeUnit) {
        if (recordInterval >= 0 && recordIntervalTimeUnit == null) {
            throw new IllegalArgumentException("recordIntervalTimeUnit must not be null when recordInterval is positive");
        }

        if (recordInterval == 0) {
            throw new IllegalArgumentException("recordInterval must not be zero");
        }
    }

    private void validateResponseStatus(@NotNull HttpStatus httpStatus, @NotNull TelegramResponse response) {
        int errorCode = response.getErrorCode();
        String description = response.getDescription();

        if (httpStatus != HttpStatus.OK) {
            throw new TelegramResponseException("HTTP error in response", httpStatus, errorCode, description);
        }

        if (!response.getOk()) { //Так как телеграм может вернуть HTTP - 200(OK), но ok(false)
            throw new TelegramResponseException("Telegram error in response", httpStatus, errorCode, description);
        }
    }
}