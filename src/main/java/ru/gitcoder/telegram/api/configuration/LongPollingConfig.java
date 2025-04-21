package ru.gitcoder.telegram.api.configuration;

import lombok.Builder;
import lombok.Getter;
import ru.gitcoder.telegram.api.update.UpdateType;

@Getter
@Builder
public class LongPollingConfig extends UpdateConfig {
    private static final int DEFAULT_LONG_POLLING_TIMEOUT = 20;
    
    private final int timeout;

    public LongPollingConfig(int timeout) {
        super(UpdateType.LONG_POLLING);
        if (timeout < 0) {
            throw new IllegalArgumentException("Timeout must be non-negative");
        }
        this.timeout = timeout;
    }

    public LongPollingConfig() {
        this(DEFAULT_LONG_POLLING_TIMEOUT);
    }
}
