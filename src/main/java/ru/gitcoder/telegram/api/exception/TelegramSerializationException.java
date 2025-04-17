package ru.gitcoder.telegram.api.exception;

public class TelegramSerializationException extends RuntimeException {
    public TelegramSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
