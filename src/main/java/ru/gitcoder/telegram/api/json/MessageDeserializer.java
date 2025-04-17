package ru.gitcoder.telegram.api.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.gitcoder.telegram.api.model.message.Message;
import ru.gitcoder.telegram.api.payload.response.update.PhotoMessage;
import ru.gitcoder.telegram.api.payload.response.update.TextMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UnknownFormatConversionException;
import java.util.function.Predicate;

public final class MessageDeserializer extends StdDeserializer<Message> {
    private final Map<Predicate<JsonNode>, Class<? extends Message>> TYPE_RESOLVER = new HashMap<Predicate<JsonNode>, Class<? extends Message>>() {{
        put(jsonNode -> jsonNode.has("text"), TextMessage.class);
        put(jsonNode -> jsonNode.has("photo"), PhotoMessage.class);
    }};

    public MessageDeserializer() {
        this(Message.class);
    }

    public MessageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonParser);

            return TYPE_RESOLVER.entrySet().stream()
                    .filter(entry -> entry.getKey().test(jsonNode))
                    .findFirst()
                    .map(entry -> objectMapper.convertValue(jsonNode, entry.getValue()))
                    .orElseThrow(() -> new UnknownFormatConversionException("Type: " + jsonNode.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
