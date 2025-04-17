package ru.gitcoder.telegram.api.model.sticker;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum StickerType {
    REGULAR("regular"),
    MASK("mask"),
    CUSTOM_EMOJI("customEmoji"),
    UNKNOWN("unknown");

    private final String name;

    @JsonCreator
    public static StickerType fromName(String name) {
        for (StickerType sticker : StickerType.values()) {
            if (sticker.getName().equals(name)) {
                return sticker;
            }
        }
        return UNKNOWN;
    }
}
