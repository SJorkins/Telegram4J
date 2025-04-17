package ru.gitcoder.telegram.api.exception;

import org.jetbrains.annotations.NotNull;

public class TelegramApiException extends RuntimeException {

    public TelegramApiException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

    public TelegramApiException(@NotNull String message) {
        super(message);
    }
}
