package ru.gitcoder.telegram.api.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ru.gitcoder.telegram.api.payload.request.TelegramRequest;
import ru.gitcoder.telegram.api.exception.TelegramSerializationException;

@Slf4j
@UtilityClass
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public String toJson(TelegramRequest<?> dto) {
        try {
            return OBJECT_MAPPER.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize Telegram DTO for request {}", e.getMessage(), e);
            throw new TelegramSerializationException("Invalid request data: serialization failed", e);
        }
    }
}
