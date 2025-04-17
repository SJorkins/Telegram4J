package ru.gitcoder.telegram.api.metrics;

import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import ru.gitcoder.telegram.api.validator.TelegramApiValidator;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MetricsFacade {
    @Delegate
    private final UpdateMetrics metrics;
    @Delegate
    private final MetricsScheduler scheduler;

    public MetricsFacade(long exportInterval, @NotNull TimeUnit exportIntervalUnit, boolean appendToFile) {
        this.metrics = new UpdateMetrics(exportInterval, exportIntervalUnit);
        this.scheduler = new MetricsScheduler(metrics, new MetricExporter(), exportInterval, exportIntervalUnit, appendToFile);
        log.debug("BotMetricsFacade initialized with export interval: {} {}", exportInterval, exportIntervalUnit);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private long exportInterval = -1;
        private TimeUnit exportIntervalUnit = TimeUnit.MINUTES;

        private boolean appendToFile = false;

        public Builder exportInterval(long exportInterval, TimeUnit exportIntervalUnit) {
            this.exportInterval = exportInterval;
            this.exportIntervalUnit = exportIntervalUnit;
            return this;
        }

        public Builder appendToFile(boolean appendToFile) {
            this.appendToFile = appendToFile;
            return this;
        }

        public MetricsFacade build() {
            TelegramApiValidator.validateMetric(exportInterval, exportIntervalUnit);
            return new MetricsFacade(exportInterval, exportIntervalUnit, appendToFile);
        }
    }
}
