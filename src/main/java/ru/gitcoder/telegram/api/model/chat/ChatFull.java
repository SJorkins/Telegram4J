package ru.gitcoder.telegram.api.model.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.birthdate.Birthdate;
import ru.gitcoder.telegram.api.model.business.Business;
import ru.gitcoder.telegram.api.model.business.BusinessLocation;
import ru.gitcoder.telegram.api.model.color.AccentColor;
import ru.gitcoder.telegram.api.model.photo.ChatPhoto;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatFull extends Chat {
    /**
     * Identifier of the accent color for the chat name and backgrounds of the chat photo, reply header,
     * and link preview.
     */
    @JsonProperty("accent_color_id")
    private AccentColor accentColor;

    /**
     * The maximum number of reactions that can be set on a message in the chat
     */
    @JsonProperty("max_reaction_count")
    private Integer maxReactionCount;

    /**
     * Optional. Chat photo
     */
    @JsonProperty("photo")
    private ChatPhoto photo;

    /**
     * Optional. If non-empty, the list of all active chat usernames; for private chats, supergroups and
     * channels
     */
    @JsonProperty("active_usernames")
    private List<String> activeUserNames = new ArrayList<>();

    /**
     * Optional. For private chats, the date of birth of the user
     */
    @JsonProperty("birthdate")
    private Birthdate birthdate;

    /**
     * Optional. For private chats with business accounts, the intro of the business
     */
    @JsonProperty("business_intro")
    private Business business;

    /**
     * Optional. For private chats with business accounts, the location of the business
     */
    @JsonProperty("business_location")
    private BusinessLocation businessLocation;
}
