package ru.gitcoder.telegram.api.exception;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatusCode;

@Getter
public class TelegramResponseException extends TelegramNetworkException {
    private final int errorCode;
    private final String description;

    public TelegramResponseException(@NotNull String message,
                                     @NotNull HttpStatusCode httpStatusCode,
                                     int errorCode,
                                     @Nullable String description) {
        super(formatMessage(message, httpStatusCode, errorCode, description), httpStatusCode);
        this.errorCode = errorCode;
        this.description = description;
    }

    private static String formatMessage(@NotNull String message,
                                        @NotNull HttpStatusCode httpStatusCode,
                                        int errorCode,
                                        @Nullable String description) {
        return String.format("%s [HTTP %s - %s], error_code=%d]",
                message, httpStatusCode, (description == null ? "" : description), errorCode);
    }
}