package ru.gitcoder.telegram.api.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import ru.gitcoder.telegram.api.exception.TelegramApiException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@UtilityClass
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlUtil {
    private static final String DEFAULT_API_ENDPOINT = "https://api.telegram.org";

    private static final String API_METHOD_URL_TEMPLATE = DEFAULT_API_ENDPOINT.concat("/bot%s/%s");
    private static final String GET_UPDATES_URL_TEMPLATE = DEFAULT_API_ENDPOINT.concat("/bot%s/getUpdates?offset=%d&timeout=%d");

    public String buildApiMethodUrl(@NotNull String accessToken, @NotNull String method) {
        return String.format(API_METHOD_URL_TEMPLATE, encodeAccessToken(accessToken), method);
    }

    public String buildGetUpdatesUrl(@NotNull String accessToken, long lastProcessedOffset, int timeout) {
        if (timeout < 0 || timeout > 100) {
            throw new IllegalArgumentException("Timeout must be between 0 and 60 seconds, got: " + timeout);
        }

        return String.format(GET_UPDATES_URL_TEMPLATE, encodeAccessToken(accessToken), lastProcessedOffset, timeout);
    }

    private String encodeAccessToken(@NotNull String accessToken) {
        try {
            return URLEncoder.encode(accessToken, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new TelegramApiException("Unsupported encode token", e);
        }
    }
}
