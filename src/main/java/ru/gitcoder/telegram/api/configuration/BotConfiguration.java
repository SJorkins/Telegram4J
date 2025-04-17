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

    private final Proxy proxy;
    private final UpdateMethodConfig updateMethodConfig;

    public BotConfiguration(@NotNull String accessToken, @Nullable UpdateMethodConfig updateMethodConfig, @Nullable Proxy proxy) {
        this.accessToken = accessToken;

        this.updateMethodConfig = updateMethodConfig == null ? new NoUpdateMethodConfig() : updateMethodConfig;
        this.proxy = proxy == null ? new NoProxy() : proxy;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String accessToken;
        private UpdateMethodConfig updateMethodConfig;
        private Proxy proxy;

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder webhookConfig(WebhookConfig webhookConfig) {
            this.updateMethodConfig = webhookConfig;
            return this;
        }

        public Builder pollingConfig(LongPollingConfig longPollingConfig) {
            this.updateMethodConfig = longPollingConfig;
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

            return new BotConfiguration(accessToken, updateMethodConfig, proxy);
        }
    }
}
