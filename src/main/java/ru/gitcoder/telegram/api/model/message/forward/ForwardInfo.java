package ru.gitcoder.telegram.api.model.message.forward;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.json.DateDeserializer;
import ru.gitcoder.telegram.api.model.Date;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = ForwardInfo.User.class, name = "user"),
                @JsonSubTypes.Type(value = ForwardInfo.HiddenUser.class, name = "hidden_user"),
                @JsonSubTypes.Type(value = ForwardInfo.Chat.class, name = "chat"),
                @JsonSubTypes.Type(value = ForwardInfo.Channel.class, name = "channel")
        })
public class ForwardInfo {
    /**
     * Type of the message origin
     */
    @JsonProperty("type")
    private ForwardType type;

    /**
     * Date the message was sent originally in Unix time
     */
    @JsonProperty("date")
    @JsonDeserialize(using = DateDeserializer.class)
    private Date date;

    @Getter
    @ToString
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class User extends ForwardInfo {
        /**
         * User that sent the message originally
         */
        @JsonProperty("sender_user")
            private ru.gitcoder.telegram.api.model.User sender;
    }

    @Getter
    @ToString
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class HiddenUser extends ForwardInfo {
        /**
         * Name of the user that sent the message originally
         */
        @JsonProperty("sender_user_name")
        private String name;
    }

    @Getter
    @ToString
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Chat extends ForwardInfo {
        /**
         * Chat that sent the message originally
         */
        @JsonProperty("sender_chat")
        private Chat senderChat;

        /**
         * Optional. For messages originally sent by an anonymous chat administrator, original message
         * author signature
         */
        @Getter
        @JsonProperty("author_signature")
        private String authorSignature;
    }

    @Getter
    @ToString
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Channel extends ForwardInfo {
        /**
         * Channel chat to which the message was originally sent
         */
        @JsonProperty("chat")
        private Chat chat;

        /**
         * Unique message identifier inside the chat
         */
        @JsonProperty("message_id")
        private Integer messageId;

        /**
         * Optional. Signature of the original post author
         */
        @JsonProperty("author_signature")
        private String authorSignature;
    }
}
