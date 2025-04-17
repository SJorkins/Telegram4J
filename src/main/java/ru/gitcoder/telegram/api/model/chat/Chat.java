package ru.gitcoder.telegram.api.model.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chat {
    /**
     * Unique identifier for this chat. This number may have more than 32 significant bits
     */
    @JsonProperty("id")
    private Long id;

    /**
     * Type of the chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    @JsonProperty("type")
    private ChatType type;

    /**
     * Optional. Title, for supergroups, channels and group chats
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Username, for private chats, supergroups and channels if available
     */
    @JsonProperty("username")
    private String username;

    /**
     * Optional. First name of the other party in a private chat
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Optional. Last name of the other party in a private chat
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Optional. True, if the supergroup chat is a forum (has topics enabled)
     */
    @JsonProperty("is_forum")
    private boolean forum;
}
