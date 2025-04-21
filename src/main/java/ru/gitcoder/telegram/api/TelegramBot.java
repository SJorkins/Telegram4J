package ru.gitcoder.telegram.api;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.gitcoder.telegram.api.configuration.BotConfiguration;
import ru.gitcoder.telegram.api.exception.TelegramApiException;
import ru.gitcoder.telegram.api.payload.response.update.Update;
import ru.gitcoder.telegram.api.service.TelegramApiService;
import ru.gitcoder.telegram.api.update.UpdateHandler;
import ru.gitcoder.telegram.api.update.UpdateManager;
import ru.gitcoder.telegram.api.update.UpdateType;
import ru.gitcoder.telegram.api.utility.HttpUtil;
import ru.gitcoder.telegram.api.validator.TelegramApiValidator;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Getter
public class TelegramBot {
    private final TelegramApiService api;
    private final BotConfiguration configuration;
    private final UpdateManager updateManager;

    public TelegramBot(@NotNull BotConfiguration configuration) {
        TelegramApiValidator.validateAccessToken(configuration.getAccessToken());

        this.configuration = configuration;
        this.api = new TelegramApiService(HttpUtil.getRestTemplate(), configuration.getAccessToken());

        this.updateManager = new UpdateManager(configuration);
    }

    public TelegramBot(@NotNull String accessToken) {
        this(new BotConfiguration(accessToken));
    }

    public void startUpdate(@NotNull UpdateType updateType, @NotNull Consumer<List<Update>> updates) {
        validateUpdateConfiguration(updateType);
        updateManager.run(updateType, updates);
    }

    public void startUpdate(@NotNull UpdateType updateType, @NotNull UpdateHandler updateHandler) {
        validateUpdateConfiguration(updateType);
        updateManager.run(updateType, updateHandler);
    }

    public void startPollingUpdate(@NotNull Consumer<List<Update>> updates) {
        validateUpdateConfiguration(UpdateType.LONG_POLLING);
        updateManager.run(UpdateType.LONG_POLLING, updates);
    }

    public void startPollingUpdate(@NotNull UpdateHandler updateHandler) {
        validateUpdateConfiguration(UpdateType.LONG_POLLING);
        updateManager.run(UpdateType.LONG_POLLING, updateHandler);
    }

    public void startWebhookUpdate(@NotNull Consumer<List<Update>> updates) {
        validateUpdateConfiguration(UpdateType.WEBHOOK);
        updateManager.run(UpdateType.WEBHOOK, updates);
    }

    public void startWebhookUpdate(@NotNull UpdateHandler updateHandler) {
        validateUpdateConfiguration(UpdateType.WEBHOOK);
        updateManager.run(UpdateType.WEBHOOK, updateHandler);
    }

    public void pauseUpdate(@NotNull Duration duration) {
        updateManager.pause(duration);
    }

    public void pauseUpdate(long time, @NotNull ChronoUnit chronoUnit) {
        pauseUpdate(Duration.of(time, chronoUnit));
    }

    public void stopUpdate() {
        updateManager.stop();
    }

    public UpdateType getUpdateType() {
        return updateManager.getUpdateType();
    }

    public boolean isRunningUpdate(@NotNull UpdateType updateType) {
        return updateManager.isRunning(updateType);
    }

    private void validateUpdateConfiguration(UpdateType updateType) {
        switch (updateType) {
            case WEBHOOK:
                if (configuration.getWebhookConfig() == null) {
                    throw new TelegramApiException("Webhook config is null");
                }
                break;
            case LONG_POLLING:
                if (configuration.getPollingConfig() == null) {
                    throw new TelegramApiException("Polling config is null");
                }
                break;
            default: {
                throw new IllegalArgumentException("Unsupported update type: " + updateType);
            }
        }
    }

    @Contract(value = " -> new", pure = true)
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BotConfiguration configuration;

        public Builder configuration(BotConfiguration configuration) {
            this.configuration = configuration;
            return this;
        }

        public TelegramBot build() {
            if (configuration == null)
                throw new TelegramApiException("Bot configuration is null");
            return new TelegramBot(configuration);
        }
    }
}

