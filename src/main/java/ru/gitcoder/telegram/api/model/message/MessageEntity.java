package ru.gitcoder.telegram.api.model.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.User;
import ru.gitcoder.telegram.api.model.language.CodeLanguage;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageEntity {
    /**
     * Type of the entity. Currently, can be “mention” (@username), “hashtag” (#hashtag or
     * #hashtag@chatusername), “cashtag” ($USD or $USD@chatusername), “bot_command”
     * (/start@jobs_bot), “url” (https://telegram.org), “email” (do-not-reply@telegram.org),
     * “phone_number” (+1-212-555-0123), “bold” (bold text), “italic” (italic text), “underline” (underlined
     * text), “strikethrough” (strikethrough text), “spoiler” (spoiler message), “blockquote” (block
     * quotation), “expandable_blockquote” (collapsed-by-default block quotation), “code” (monowidth
     * string), “pre” (monowidth block), “text_link” (for clickable text URLs), “text_mention” (for users
     * without usernames), “custom_emoji” (for inline custom emoji stickers)
     */
    @JsonProperty("type")
    private String type;

    /**
     * Offset in UTF-16 code units to the start of the entity
     */
    @JsonProperty("offset")
    private int offset;

    /**
     * Length of the entity in <a href="https://core.telegram.org/api/entities#entity-length">...</a>
     */
    @JsonProperty("length")
    private int length;

    /**
     * Optional. For “text_link” only, URL that will be opened after user taps on the text
     */
    @JsonProperty("url")
    private String url;

    /**
     * Optional. For “text_mention” only, the mentioned user
     */
    @JsonProperty("user")
    private User user;

    /**
     * Optional. For “pre” only, the programming language of the entity text
     */
    @JsonProperty("language")
    private CodeLanguage language;

    /**
     * Optional. For “custom_emoji” only, unique identifier of the custom emoji. Use
     * getCustomEmojiStickers to get full information about the sticker
     */
    @JsonProperty("custom_emoji_id")
    private String customEmojiId;
}
