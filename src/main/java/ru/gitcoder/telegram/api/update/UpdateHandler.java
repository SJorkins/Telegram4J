package ru.gitcoder.telegram.api.update;

import ru.gitcoder.telegram.api.payload.response.update.PhotoMessage;
import ru.gitcoder.telegram.api.payload.response.update.TextMessage;
import ru.gitcoder.telegram.api.payload.response.update.Update;

public class UpdateHandler {

    public void handle(Update update) {}

    public void handle(TextMessage message) {}

    public void handle(PhotoMessage message) {}
}
