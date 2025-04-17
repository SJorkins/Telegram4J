package ru.gitcoder.telegram.api.metrics;

import lombok.extern.slf4j.Slf4j;
import ru.gitcoder.telegram.api.utility.FileUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
public final class MetricExporter {
    private static final String DIR = "metrics";
    private static final String FILE = "bot_metrics.txt";

    private static final Path METRICS_PATH = Paths.get(DIR, FILE);

    public void doExport(UpdateMetrics metrics, boolean append) {
        try {
            Path resourcePath = getMetricsPath();
            FileUtil.writeToFile(resourcePath, formatMetrics(metrics), append);

            log.debug("Exported metrics to file: {}", resourcePath);
        } catch (IOException e) {
            log.error("Failed to export metrics to file: {}", e.getMessage(), e);
            throw new IllegalStateException("Error exporting metrics to file", e);
        }
    }

    private Path getMetricsPath() throws IOException {
        String resourceDir = Objects.requireNonNull(UpdateMetrics.class.getResource("/")).getPath();
        if (resourceDir == null) {
            throw new IOException("Unable to locate resources directory");
        }
        return Paths.get(resourceDir).resolve(METRICS_PATH).normalize();
    }

    private String formatMetrics(UpdateMetrics metrics) {
        StringBuilder sb = new StringBuilder();
        sb.append("Total updates received: ").append(metrics.getUpdateCount()).append("\n")
                .append("Total work time: ").append(metrics.getWorkTimeMillis()).append(" ms\n")
                .append("Average processing time: ").append(String.format("%.2f", metrics.getAverageWorkTimeMillis())).append(" ms\n")
                .append("Breakdown by type:\n");

        metrics.getMetricsSummary().forEach(metric ->
                sb.append("  ").append(metric.getUpdateClass().getSimpleName())
                        .append(": ").append(metric.getReceivedCount()).append("\n"));

        return sb.toString();
    }
}
