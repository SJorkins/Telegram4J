package ru.gitcoder.telegram.api.update.polling;

import lombok.Builder;
import ru.gitcoder.telegram.api.service.LongPollingService;

public class LongPollingFacade {
    private final LongPollingService longPollingService;

    @Builder
    public LongPollingFacade(int timeout) {
        this.longPollingService = new LongPollingService(timeout); //todo
    }

    public void stop() {
        longPollingService.shutdown();
    }

    public boolean isActive() {
        return longPollingService.isRunning();
    }

    public void pause(long millis) {
        longPollingService.pause(millis);
    }

    public void resume() {
        longPollingService.resume();
    }
}
