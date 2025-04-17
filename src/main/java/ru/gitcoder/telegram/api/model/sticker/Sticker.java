package ru.gitcoder.telegram.api.model.sticker;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import ru.gitcoder.telegram.api.model.MaskPosition;
import ru.gitcoder.telegram.api.model.TelegramFile;
import ru.gitcoder.telegram.api.model.media.Media;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sticker {
    /**
     * Information about this sticker (size, file)
     */
    @JsonUnwrapped
    private Media media;

    /**
     * Type of the sticker, currently one of “regular”, “mask”, “custom_emoji”. The type of the sticker is
     * independent of its format, which is determined by the fields is_animated and is_video.
     */
    @JsonProperty("type")
    private StickerType type;

    /**
     * True, if the sticker is animated
     */
    @JsonProperty("is_animated")
    private boolean animated;

    /**
     * True, if the sticker is a video sticker
     */
    @JsonProperty("is_video")
    private boolean video;

    /**
     * Optional. Sticker thumbnail in the .WEBP or .JPG format
     */
    @JsonUnwrapped
    private Media thumbnail;

    /**
     * Optional. Emoji associated with the sticker
     */
    @JsonProperty("emoji")
    private String emoji;

    /**
     * Optional. Name of the sticker set to which the sticker belongs
     */
    @JsonProperty("set_name")
    private String collectName;

    /**
     * Optional. For premium regular stickers, premium animation for the sticker
     */
    @JsonProperty("premium_animation")
    private TelegramFile file;

    /**
     * Optional. For mask stickers, the position where the mask should be placed
     */
    @JsonProperty("mask_position")
    private MaskPosition position;

    /**
     * Optional. For custom emoji stickers, unique identifier of the custom emoji
     */
    @JsonProperty("custom_emoji_id")
    private String emojiId;

    /**
     * Optional. True, if the sticker must be repainted to a text color in messages, the color of the
     * Telegram Premium badge in emoji status, white color on chat photos, or another appropriate color
     * in other places
     */
    @Getter(AccessLevel.NONE)
    @JsonProperty("needs_repainting")
    private boolean needRepainting;

    public boolean needRepainting() {
        return needRepainting;
    }
}
