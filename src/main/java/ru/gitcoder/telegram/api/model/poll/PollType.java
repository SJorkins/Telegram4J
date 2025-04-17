package ru.gitcoder.telegram.api.model.poll;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter(onMethod_ = @JsonValue)
@RequiredArgsConstructor
public enum PollType {

    REGULAR("regular"),
    QUIZ("quiz"),
    UNKNOWN("unknown");

    private final String name;

    @JsonCreator
    public static PollType fromName(String name) {
        for (PollType poll : PollType.values()) {
            if (poll.getName().equals(name)) {
                return poll;
            }
        }
        return UNKNOWN;
    }
}
