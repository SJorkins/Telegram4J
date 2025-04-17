package ru.gitcoder.telegram.api.model.chat;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum ChatType {
    PRIVATE("private"),
    GROUP("group"),
    SUPERGROUP("supergroup"),
    CHANNEL("channel"),
    UNKNOWN("unknown");

    private final String name;

    @JsonCreator
    public static ChatType fromName(String name) {
        for (ChatType chatType : values()) {
            if (chatType.name.equals(name)) {
                return chatType;
            }
        }
        return ChatType.UNKNOWN;
    }
}