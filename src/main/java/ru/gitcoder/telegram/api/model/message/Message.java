package ru.gitcoder.telegram.api.model.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.json.DateDeserializer;
import ru.gitcoder.telegram.api.model.chat.Chat;
import ru.gitcoder.telegram.api.model.Date;
import ru.gitcoder.telegram.api.model.User;
import ru.gitcoder.telegram.api.model.message.forward.ForwardMessage;
import ru.gitcoder.telegram.api.model.reply.ExternalReplyInfo;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    /**
     * Unique message identifier inside this chat. In specific instances (e.g., message containing a video
     * sent to a big chat), the server might automatically schedule a message instead of sending it
     * immediately. In such cases, this field will be 0 and the relevant message will be unusable until it is
     * actually sent
     */
    @JsonProperty("message_id")
    private Long id;

    /**
     * Optional. Unique identifier of a message thread to which the message belongs; for supergroups
     * only
     */
    @JsonProperty("message_thread_id")
    private Integer messageThreadId;

    /**
     * Optional. Sender of the message; may be empty for messages sent to channels. For backward
     * compatibility, if the message was sent on behalf of a chat, the field contains a fake sender user in
     * non-channel chats
     */
    @JsonProperty("from")
    private User from;

    /**
     * Optional. Sender of the message when sent on behalf of a chat. For example, the supergroup
     * itself for messages sent by its anonymous administrators or a linked channel for messages
     * automatically forwarded to the channel's discussion group. For backward compatibility, if the
     * message was sent on behalf of a chat, the field from contains a fake sender user in non-channel
     * chats.
     */
    @JsonProperty("sender_chat")
    private Chat senderChat;

    /**
     * Optional. If the sender of the message boosted the chat, the number of boosts added by the user
     */
    @JsonProperty("sender_boost_count")
    private Integer boostCount;

    /**
     * 	Optional. The bot that actually sent the message on behalf of the business account. Available
     * 	only for outgoing messages sent on behalf of the connected business account.
     */
    @JsonProperty("sender_business_bot")
    private User businessBot;

    /**
     * Date the message was sent. It is always a positive number, representing a valid date.
     */
    @JsonProperty("date")
    @JsonDeserialize(using = DateDeserializer.class)
    private Date date;

    /**
     * Optional. Unique identifier of the business connection from which the message was received. If
     * non-empty, the message belongs to a chat of the corresponding business account that is
     * independent of any potential bot chat which might share the same identifier.
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Optional. Sender of the message when sent on behalf of a chat. For example, the supergroup
     * itself for messages sent by its anonymous administrators or a linked channel for messages
     * automatically forwarded to the channel's discussion group. For backward compatibility, if the
     * message was sent on behalf of a chat, the field from contains a fake sender user in non-channel
     * chats.
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Optional. Information about the original message for forwarded messages
     */
    @JsonProperty("forward_origin")
    private ForwardMessage forwardMessage;

    /**
     * Optional. True, if the message is sent to a forum topic
     */
    @JsonProperty("is_topic_message")
    private boolean topic;

    /**
     * Optional. True, if the message is a channel post that was automatically forwarded to the
     * connected discussion group
     */
    @JsonProperty("is_automatic_forward")
    private boolean automaticForward;

    /**
     * 	Optional. For replies in the same chat and message thread, the original message. Note that the
     * 	Message object in this field will not contain further reply_to_message fields even if it itself is a
     * 	reply.
     */
    @JsonProperty("reply_to_message")
    private Message replyToMessage;

    /**
     * Optional. Information about the message that is being replied to, which may come from another
     * chat or forum topic
     */
    @JsonProperty("external_reply")
    private ExternalReplyInfo replyInfo;

    /**
     * Optional. Bot through which the message was sent
     */
    @JsonProperty("via_bot")
    private User viaBot;

    /**
     * 	Optional. True, if the message was sent by an implicit action, for example, as an away or a
     * 	greeting business message, or as a scheduled message
     */
    @JsonProperty("from_offline")
    private boolean fromOffline;
}
