package ru.gitcoder.telegram.api.exception;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;

@Getter
public class TelegramNetworkException extends TelegramApiException {
    private final HttpStatusCode httpStatusCode;

    public TelegramNetworkException(@NotNull String message, @NotNull Throwable cause,
                                    @NotNull HttpStatusCode httpStatusCode) {
        super(formatMessage(message, httpStatusCode), cause);
        this.httpStatusCode = httpStatusCode;
    }

    public TelegramNetworkException(@NotNull String message, @NotNull HttpStatusCode httpStatusCode) {
        super(formatMessage(message, httpStatusCode));
        this.httpStatusCode = httpStatusCode;
    }

    private static String formatMessage(@NotNull String message, @NotNull HttpStatusCode httpStatusCode) {
        return String.format("%s [HTTP %s]", message, httpStatusCode);
    }
}
