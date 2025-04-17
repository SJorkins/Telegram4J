package ru.gitcoder.telegram.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAbout implements TelegramModel {
    /**
     * True, if this user is a bot
     */
    @JsonProperty("is_bot")
    private Boolean bot;

    /**
     * 	Optional. True, if this user is a Telegram Premium user
     */
    @JsonProperty("is_premium")
    private Boolean premium;

    /**
     * Optional. True, if this user added the bot to the attachment menu
     */
    @JsonProperty("added_to_attachment_menu")
    private Boolean addedToAttachmentMenu;

    /**
     * Optional. True, if the bot can be invited to groups. Returned only in getMe.
     */
    @Getter(AccessLevel.NONE)
    @JsonProperty("can_join_groups")
    private Boolean canJoinGroups;

    /**
     * Optional. True, if privacy mode is disabled for the bot. Returned only in getMe.
     */
    @Getter(AccessLevel.NONE)
    @JsonProperty("can_read_all_group_messages")
    private Boolean canReadAllGroupMessages;

    /**
     * Optional. True, if the bot supports inline queries. Returned only in getMe.
     */
    @Getter
    @JsonProperty("supports_inline_queries")
    private Boolean supportsInlineQueries;

    /**
     * Optional. True, if the bot can be connected to a Telegram Business account to receive its
     * messages. Returned only in getMe.
     */
    @Getter(AccessLevel.NONE)
    @JsonProperty("can_connect_to_business")
    private Boolean canConnectToBusiness;

    /**
     * Optional. True, if the bot has a main Web App. Returned only in getMe.
     */
    @Getter(AccessLevel.NONE)
    @JsonProperty("has_main_web_app")
    private Boolean hasMainWebApp;

    public boolean canJoinGroups() {
        return canJoinGroups;
    }

    public boolean canReadAllGroupMessages() {
        return canReadAllGroupMessages;
    }

    public boolean canConnectToBusiness() {
        return canConnectToBusiness;
    }

    public boolean hasMainWebApp() {
        return hasMainWebApp;
    }
}
