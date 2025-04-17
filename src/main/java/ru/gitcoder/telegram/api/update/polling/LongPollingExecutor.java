package ru.gitcoder.telegram.api.update.polling;

import lombok.extern.slf4j.Slf4j;
import ru.gitcoder.telegram.api.exception.TelegramApiException;
import ru.gitcoder.telegram.api.utility.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public final class LongPollingExecutor {
    private final ExecutorService executorService;

    private static final String THREAD_NAME = "Telegram-Long-Polling-Thread";

    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean paused = new AtomicBoolean(false);

    private static final int DEFAULT_POLLING_DELAY_MS = 100;
    private static final int DEFAULT_ERROR_DELAY_MS = 2000;

    private static final long DEFAULT_RETRY_DELAY_MS = 5000;

    private final int pollingDelayMs;
    private final int errorDelayMs;

    private static final String LONG_POLLING_IS_RUNNING_MSG = "LongPolling is already running";
    private static final String LONG_POLLING_IS_STOPPED_MSG = "Long polling is already stopped";

    public LongPollingExecutor(int pollingDelayMs, int errorDelayMs) {
        if (pollingDelayMs < 0 || errorDelayMs < 0) {
            throw new IllegalArgumentException("Delays must not be negative: pollingDelayMs=" + pollingDelayMs + ", errorDelayMs=" + errorDelayMs);
        }

        this.pollingDelayMs = pollingDelayMs;
        this.errorDelayMs = errorDelayMs;
        this.executorService = createExecutorService();
    }

    private ExecutorService createExecutorService() {
        return Executors.newSingleThreadExecutor(ThreadUtil.createThreadFactory(THREAD_NAME, true));
    }

    public LongPollingExecutor() {
        this(DEFAULT_POLLING_DELAY_MS, DEFAULT_ERROR_DELAY_MS);
    }

    public void start(Runnable pollingTask) {
        validatePollingTask(pollingTask);

        if (isRunning()) {
            printLongPollingIsRunning();
            return;
        }

        running.set(true);

        log.debug("Starting long polling executor");
        executorService.submit(() -> runPollingLoop(pollingTask));
    }

    public void retry() {
        try {
            Thread.sleep(DEFAULT_RETRY_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Retry long polling interrupted", e);
        }
    }

    public void resume() {
        if (!isPaused()) {
            printLongPollingIsRunning();
            return;
        }

        paused.set(false);
        log.debug("Long polling resumed forced");
    }

    @SuppressWarnings("BusyWait")
    private void runPollingLoop(Runnable pollingTask) {
        while (isRunning()) {
            if (!paused.get()) {
                try {
                    pollingTask.run();
                    Thread.sleep(pollingDelayMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    shutdown();
                    break;
                } catch (Exception e) {
                    log.error("Unexpected error during polling: {}", e.getMessage(), e);
                    sleepOnError();
                }
            }
        }
        log.debug("Long polling executor stopped");
    }

    private void validatePollingTask(Runnable pollingTask) {
        if (pollingTask == null) {
            throw new TelegramApiException("Polling task must not be null");
        }
    }

    public void shutdown() {
        if (!isRunning()) {
            printLongPollingIsStopped();
            return;
        }

        running.set(false);
        executorService.shutdown();

        log.debug("Long polling executor shutdown initiated");
    }

    private void sleepOnError() {
        try {
            Thread.sleep(errorDelayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            running.set(false);
        }
    }

    public void pause(long millis) {
        if (millis <= 0) {
            throw new IllegalArgumentException("Pause duration must not be negative: " + millis);
        }

        if (!isRunning()) {
            printLongPollingIsStopped();
            return;
        }

        paused.set(true);
        log.debug("Pausing long polling for {} milliseconds", millis);

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            shutdown();
        } finally {
            if (isPaused()) {
                paused.set(false);
                log.debug("Long polling resumed after pause");
            }
        }
    }

    private void printLongPollingIsStopped() {
        log.warn(LONG_POLLING_IS_STOPPED_MSG);
    }

    private void printLongPollingIsRunning() {
        log.warn(LONG_POLLING_IS_RUNNING_MSG);
    }

    public boolean isRunning() {
        return running.get();
    }

    public boolean isPaused() {
        return paused.get();
    }
}
