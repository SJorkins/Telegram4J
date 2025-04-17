package ru.gitcoder.telegram.api.model.giveaway;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import ru.gitcoder.telegram.api.json.DateDeserializer;
import ru.gitcoder.telegram.api.model.chat.Chat;
import ru.gitcoder.telegram.api.model.Date;
import ru.gitcoder.telegram.api.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiveawayWinners {
    /**
     * The chat that created the giveaway
     */
    @JsonProperty("chat")
    private Chat creatorChat;

    /**
     * Identifier of the message with the giveaway in the chat
     */
    @JsonProperty("giveaway_message_id")
    private long id;

    /**
     * Point in time when winners of the giveaway were selected
     */
    @JsonProperty("winners_selection_date")
    @JsonDeserialize(using = DateDeserializer.class)
    private Date selectionDate;

    /**
     * Total number of winners in the giveaway
     */
    @JsonProperty("winner_count")
    private int winnersCount;

    /**
     * List of up to 100 winners of the giveaway
     */
    @JsonProperty("winners")
    private List<User> winners = new ArrayList<>();

    @JsonUnwrapped
    private GiveawayCondition condition;

    @JsonUnwrapped
    private GiveawayPrize prize;
}
