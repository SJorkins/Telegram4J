package ru.gitcoder.telegram.api.metrics;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import ru.gitcoder.telegram.api.utility.ThreadUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MetricsScheduler {
    private static final String THREAD_NAME = "Telegram-Metrics-Thread";

    private final ScheduledExecutorService scheduler;
    private final UpdateMetrics metrics;
    private final MetricExporter exporter;

    private volatile long exportInterval;
    private volatile TimeUnit exportIntervalUnit;
    @Setter
    private volatile boolean appendToFile;
    private volatile boolean running;

    public MetricsScheduler(UpdateMetrics metrics, MetricExporter exporter,
                            long exportInterval, @NotNull TimeUnit exportIntervalUnit, boolean appendToFile) {
        this.scheduler = createScheduledExecutorService();
        this.exporter = exporter;
        this.metrics = metrics;

        this.exportInterval = exportInterval;
        this.exportIntervalUnit = exportIntervalUnit;
        this.appendToFile = appendToFile;

        if (isEnabled()) {
            start();
        }
    }

    private ScheduledExecutorService createScheduledExecutorService() {
        return Executors.newSingleThreadScheduledExecutor(ThreadUtil.createThreadFactory(THREAD_NAME, true));
    }

    private void start() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                exporter.doExport(metrics, appendToFile);
            } catch (Exception e) {
                log.error("Failed to start metrics exporter", e);
            }
        }, exportInterval, exportInterval, exportIntervalUnit);

        running = true;
        log.debug("Metrics started with interval: {} {}", exportInterval, exportIntervalUnit);
    }

    private void restart() {
        if (isRunning()) {
            stop();

            if (isEnabled()) {
                start();
                log.debug("Metrics restarted with new interval: {} {}", exportInterval, exportIntervalUnit);
            } else {
                log.warn("Metrics stopped due to inactive state");
            }
        } else if (isEnabled()) {
            start();
        }
    }

    public void stop() {
        if (!isRunning()) {
            log.warn("Metrics is already stopped");
            return;
        }

        scheduler.shutdown();

        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
                log.warn("Metrics scheduler did not terminate gracefully");
            }
        } catch (InterruptedException exception) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
            log.error("Interrupted while shutting down metrics scheduler", exception);
        }

        running = false;
        log.debug("Metrics scheduler shut down");
    }

    public void setExportInterval(long exportInterval) {
        if (exportInterval <= 0) {
            throw new IllegalArgumentException("recordInterval must be greater than 0");
        }

        this.exportInterval = exportInterval;

        log.debug("Setting export interval: {} {}", exportInterval, exportIntervalUnit);
        restart();
    }

    public void setExportIntervalUnit(@NotNull TimeUnit exportIntervalUnit) {
        this.exportIntervalUnit = exportIntervalUnit;

        log.debug("Setting export interval unit: {}", exportIntervalUnit);
        restart();
    }

    private boolean isRunning() {
        return running;
    }

    public boolean isEnabled() {
        return exportInterval > 0 && isRunning();
    }
}
