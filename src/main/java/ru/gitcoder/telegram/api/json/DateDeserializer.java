package ru.gitcoder.telegram.api.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.gitcoder.telegram.api.model.Date;

import java.io.IOException;

public class DateDeserializer extends StdDeserializer<Date> {

    public DateDeserializer() {
        super(Date.class);
    }

    public DateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        try {
            long timestamp = jsonParser.getValueAsLong();
            return new Date(timestamp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
