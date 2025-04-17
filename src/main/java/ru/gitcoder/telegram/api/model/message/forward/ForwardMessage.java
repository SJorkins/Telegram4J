package ru.gitcoder.telegram.api.model.message.forward;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForwardMessage {
    private ForwardInfo info; //todo: запарсить
    /**
     * Creates an adapter for fluent processing of this forwarded message origin.
     *
     * @return a {@link ForwardProcessor} for chaining actions
     */
    public ForwardProcessor process() {
        return new ForwardProcessor(info);
    }

    /**
     * Checks if the message was forwarded from a user.
     *
     * @return true if the origin is a user
     */
    public boolean fromUser() {
        return info.getType() == ForwardType.USER;
    }

    /**
     * Checks if the message was forwarded from an anonymous user.
     *
     * @return true if the origin is an anonymous user
     */
    public boolean fromAnonymous() {
        return info.getType() == ForwardType.HIDDEN_USER;
    }

    /**
     * Checks if the message was forwarded from a group or supergroup.
     *
     * @return true if the origin is a group
     */
    public boolean fromChat() {
        return info.getType() == ForwardType.CHAT;
    }

    /**
     * Checks if the message was forwarded from a channel.
     *
     * @return true if the origin is a channel
     */
    public boolean fromChannel() {
        return info.getType() == ForwardType.CHANNEL;
    }
}