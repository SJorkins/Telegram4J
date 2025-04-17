package ru.gitcoder.telegram.api.update;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.gitcoder.telegram.api.exception.TelegramApiException;
import ru.gitcoder.telegram.api.payload.response.update.Update;

import java.util.List;
import java.util.function.Consumer;

@Getter
@Slf4j
public class UpdateManager {
    private UpdateMethod currenUpdateMethod;

    public void run(UpdateMethod updateMethod, UpdateHandler updateHandler) {
        this.currenUpdateMethod = updateMethod;

        //longPollingService.initiate(accessToken, new UpdateConsumerAdapter(updatesConsumer));
    }

    public void run(UpdateMethod updateMethod, Consumer<List<Update>> updates) {
        if (updates == null) {
            throw new TelegramApiException("Updates consumer has not been initialized");
        }
        this.currenUpdateMethod = updateMethod;
        log.debug("Starting update processing via: {}", updateMethod);
    }

    public void stop() {

    }

    public boolean isRunning() {
        return false;
    }
}
