package ru.gitcoder.telegram.api;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.gitcoder.telegram.api.configuration.BotConfiguration;
import ru.gitcoder.telegram.api.exception.TelegramApiException;
import ru.gitcoder.telegram.api.update.UpdateManager;
import ru.gitcoder.telegram.api.update.UpdateMethod;
import ru.gitcoder.telegram.api.payload.response.update.Update;
import ru.gitcoder.telegram.api.service.TelegramApiService;
import ru.gitcoder.telegram.api.update.UpdateHandler;
import ru.gitcoder.telegram.api.utility.HttpUtil;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Getter
public class TelegramBot {
    private final UpdateManager updateManager = new UpdateManager();

    private final TelegramApiService api;
    private final BotConfiguration configuration;

    public TelegramBot(@NotNull BotConfiguration configuration) {
        validateConfiguration(configuration);
        this.configuration = configuration;
        this.api = new TelegramApiService(HttpUtil.getRestTemplate(), configuration.getAccessToken());
    }

    public void startUpdate(UpdateMethod updateMethod, Consumer<List<Update>> updates) {
        updateManager.run(updateMethod, updates);
    }

    public void startUpdate(UpdateMethod updateMethod, UpdateHandler updateHandler) {
        updateManager.run(updateMethod, updateHandler);
    }

    private static void validateConfiguration(BotConfiguration configuration) {
        String accessToken = configuration.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            throw new TelegramApiException("Access token is not initialized");
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
            validateConfiguration(configuration);
            return new TelegramBot(configuration);
        }
    }
}
