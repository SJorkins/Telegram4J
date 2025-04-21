package ru.gitcoder.telegram.api.configuration;

import lombok.Builder;
import lombok.Getter;
import ru.gitcoder.telegram.api.update.UpdateType;

@Getter
@Builder
public class WebhookConfig extends UpdateConfig {
    private final String webhookUrl;

    public WebhookConfig(String webhookUrl) {
        super(UpdateType.WEBHOOK);
        if (webhookUrl == null || webhookUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Webhook URL must not be null or empty");
        }
        this.webhookUrl = webhookUrl;
    }
}
