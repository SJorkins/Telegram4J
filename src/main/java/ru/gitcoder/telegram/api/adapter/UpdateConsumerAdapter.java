package ru.gitcoder.telegram.api.adapter;

import lombok.extern.slf4j.Slf4j;
import ru.gitcoder.telegram.api.payload.response.update.Update;

import java.util.function.Consumer;

@Slf4j
public class UpdateConsumerAdapter extends LongPollingProcessor { //todo
    private final Consumer<Update> updateConsumer;

    public UpdateConsumerAdapter(Consumer<Update> updateConsumer) {
        this.updateConsumer = updateConsumer;
    }

    @Override
    public void process(Update update) {
        try {
            updateConsumer.accept(update);
        } catch (Exception e) {
            log.error("Failed to process update: {}", e.getMessage(), e);
            throw e;
        }
    }
}