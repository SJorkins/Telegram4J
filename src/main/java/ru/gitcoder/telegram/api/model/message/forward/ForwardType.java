package ru.gitcoder.telegram.api.model.message.forward;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter(onMethod_ = @JsonValue)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum ForwardType {
    USER("user"),
    HIDDEN_USER("hidden_user"),
    CHAT("chat"),
    CHANNEL("channel"),
    UNKNOWN(null);

    private final String jsonValue;

    private static final Map<String, ForwardType> BY_VALUE = new HashMap<>();

    static {
        for (ForwardType type : values()) {
            BY_VALUE.put(type.jsonValue, type);
        }
        BY_VALUE.put("", UNKNOWN);
    }

    @JsonCreator
    public static ForwardType fromValue(String value) {
        return BY_VALUE.getOrDefault(value != null ? value.toLowerCase() : "", UNKNOWN);
    }
}