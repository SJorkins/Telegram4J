package ru.gitcoder.telegram.api.configuration;

import lombok.Builder;
import lombok.Getter;
import ru.gitcoder.telegram.api.update.UpdateMethod;

@Getter
@Builder
public class LongPollingConfig extends UpdateMethodConfig {
    private static final int DEFAULT_LONG_POLLING_TIMEOUT = 20;
    
    private final int pollingTimeout;

    public LongPollingConfig(int pollingTimeout) {
        super(UpdateMethod.LONG_POLLING);
        if (pollingTimeout < 0) {
            throw new IllegalArgumentException("Timeout must be non-negative");
        }
        this.pollingTimeout = pollingTimeout;
    }

    public LongPollingConfig() {
        this(DEFAULT_LONG_POLLING_TIMEOUT);
    }
}
