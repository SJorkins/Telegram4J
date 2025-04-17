package ru.gitcoder.telegram.api.metrics;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import ru.gitcoder.telegram.api.payload.response.update.Update;
import ru.gitcoder.telegram.api.validator.TelegramApiValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.DoubleAdder;

@Slf4j
public final class UpdateMetrics {
    private final DoubleAdder totalUpdateCount = new DoubleAdder();
    private final DoubleAdder totalWorkTimeMillis = new DoubleAdder();

    private final Map<Class<? extends Update>, UpdateMetric> receivedTypes = new ConcurrentHashMap<>();

    public UpdateMetrics(long exportInterval, @NotNull TimeUnit exportIntervalUnit) {
        TelegramApiValidator.validateMetric(exportInterval, exportIntervalUnit);
        log.debug("Metrics initialized with export interval of {} millis", exportInterval);
    }

    public void recordUpdate(@NotNull Update update, double processingTimeMs) {
        totalUpdateCount.add(1);
        totalWorkTimeMillis.add(processingTimeMs);

        UpdateMetric updateMetric = receivedTypes.computeIfAbsent(update.getClass(), UpdateMetric::new);
        updateMetric.incrementReceivedCount();

        log.trace("Recorded message metrics: type={}, count={}, processingTimeMs={}",
                update.getClass().getSimpleName(), updateMetric.getReceivedCount(), String.format("%.2f", processingTimeMs));
    }

    public long getUpdateCount() {
        return totalUpdateCount.longValue();
    }

    public long getReceivedTypedUpdateCount(@NotNull Class<? extends Update> clazz) {
        return receivedTypes.getOrDefault(clazz, new UpdateMetric(clazz)).getReceivedCount();
    }

    public long getWorkTimeMillis() {
        return totalWorkTimeMillis.longValue();
    }

    public long getWorkTime(TimeUnit timeUnit) {
        long workTime = totalWorkTimeMillis.longValue();
        return TimeUnit.MILLISECONDS.convert(workTime, timeUnit);
    }

    public double getAverageWorkTimeMillis() {
        long count = totalUpdateCount.longValue();
        return count > 0 ? totalWorkTimeMillis.sum() / count : 0;
    }

    public double getAverageWorkTime(TimeUnit timeUnit) {
        long count = totalUpdateCount.longValue();
        double averageWorkTime = count > 0 ? totalWorkTimeMillis.sum() / count : 0;

        return TimeUnit.MILLISECONDS.convert((long) averageWorkTime, timeUnit);
    }

    public List<UpdateMetric> getMetricsSummary() {
        return new ArrayList<>(receivedTypes.values());
    }

    public void reset() {
        long oldTotalUpdateCount = totalUpdateCount.longValue();

        receivedTypes.clear();
        totalUpdateCount.reset();
        totalWorkTimeMillis.reset();

        log.debug("Metrics reset. Previous messages received={}", oldTotalUpdateCount);
    }
}
