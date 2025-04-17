package ru.gitcoder.telegram.api.configuration;

import lombok.Builder;
import lombok.Getter;
import ru.gitcoder.telegram.api.update.UpdateMethod;

@Getter
@Builder
public class WebhookConfig extends UpdateMethodConfig {
    private final String webhookUrl;

    public WebhookConfig(String webhookUrl) {
        super(UpdateMethod.WEBHOOK);
        if (webhookUrl == null || webhookUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Webhook URL must not be null or empty");
        }
        this.webhookUrl = webhookUrl;
    }
}
