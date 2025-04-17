package ru.gitcoder.telegram.api.metrics;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.payload.response.update.Update;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class UpdateMetric {
    private final Class<? extends Update> updateClass;
    private long receivedCount = 0;

    public void incrementReceivedCount() {
        receivedCount += 1;
    }
}
