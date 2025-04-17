package ru.gitcoder.telegram.api.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@UtilityClass
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtil {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public HttpEntity<String> createHttpEntity(@NotNull String json) {
        HttpHeaders headers = createHttpHeaders();
        return new HttpEntity<>(json, headers);
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public static RestTemplate getRestTemplate() {
        return REST_TEMPLATE;
    }
}
