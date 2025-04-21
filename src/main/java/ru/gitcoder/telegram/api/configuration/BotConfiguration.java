package ru.gitcoder.telegram.api.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.gitcoder.telegram.api.proxy.Proxy;

@Getter
@ToString
@EqualsAndHashCode
public final class BotConfiguration {
    private final String accessToken;

    private final UpdateConfig webhookConfig;
    private final UpdateConfig pollingConfig;

    private final Proxy proxy;

    public BotConfiguration(@NotNull String accessToken,
                            @Nullable LongPollingConfig longPollingConfig,
                            @Nullable WebhookConfig webhookConfig,
                            @Nullable Proxy proxy) {
        this.accessToken = accessToken;

        this.webhookConfig = webhookConfig == null ? new NoUpdateConfig() : webhookConfig;
        this.pollingConfig = longPollingConfig == null ? new NoUpdateConfig() : longPollingConfig;

        this.proxy = proxy == null ? new NoProxy() : proxy;
    }

    public BotConfiguration(@NotNull String accessToken,
                            @Nullable WebhookConfig webhookConfig,
                            @Nullable Proxy proxy) {
        this(accessToken, null, webhookConfig, proxy);
    }

    public BotConfiguration(@NotNull String accessToken,
                            @Nullable LongPollingConfig longPollingConfig,
                            @Nullable Proxy proxy) {
        this(accessToken, longPollingConfig, null, proxy);
    }

    public BotConfiguration(@NotNull String accessToken, Proxy proxy) {
        this(accessToken, null, null, proxy);
    }

    public BotConfiguration(@NotNull String accessToken) {
        this(accessToken, null, null, null);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String accessToken;

        private WebhookConfig webhookConfig;
        private LongPollingConfig pollingConfig;

        private Proxy proxy;

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder webhookConfig(WebhookConfig webhookConfig) {
            this.webhookConfig = webhookConfig;
            return this;
        }

        public Builder pollingConfig(LongPollingConfig longPollingConfig) {
            this.pollingConfig = longPollingConfig;
            return this;
        }

        public Builder proxy(Proxy proxy) {
            validateProxy(proxy);
            this.proxy = proxy;
            return this;
        }

        private void validateProxy(Proxy proxy) {
            if (proxy == null) {
                return;
            }
            if (proxy.getHost() == null || proxy.getHost().trim().isEmpty()) {
                throw new IllegalStateException("Proxy host must not be null or empty if proxy is specified");
            }
            if (proxy.getPort() == null || proxy.getPort() <= 0 || proxy.getPort() > 65535) {
                throw new IllegalStateException("Proxy port must be between 1 and 65535");
            }
        }

        public BotConfiguration build() {
            if (accessToken == null) {
                throw new IllegalStateException("Bot access token not set");
            }

            return new BotConfiguration(accessToken, pollingConfig, webhookConfig, proxy);
        }
    }
}
