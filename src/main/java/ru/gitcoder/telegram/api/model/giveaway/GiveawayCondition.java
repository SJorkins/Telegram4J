package ru.gitcoder.telegram.api.model.giveaway;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiveawayCondition {
    /**
     * Optional. The number of other chats the user had to join in order to be eligible for the giveaway
     */
    @JsonProperty("additional_chat_count")
    private int additionalChatCount;

    /**
     * Optional. True, if only users who had joined the chats after the giveaway started were eligible to
     * win
     */
    @JsonProperty("only_new_members")
    private boolean onlyNewMembers;
}
