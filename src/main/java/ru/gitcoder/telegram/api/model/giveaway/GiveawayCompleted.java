package ru.gitcoder.telegram.api.model.giveaway;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.message.Message;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiveawayCompleted {
    /**
     * Number of winners in the giveaway
     */
    @JsonProperty("winner_count")
    private int winnerCount;

    /**
     * Optional. Number of undistributed prizes
     */
    @JsonProperty("unclaimed_prize_count")
    private int unclaimed_prize_count;

    /**
     * Optional. Message with the giveaway that was completed, if it wasn't deleted
     */
    @JsonProperty("giveaway_message")
    private Message message;

    /**
     * Optional. True, if the giveaway is a Telegram Star giveaway. Otherwise, currently, the giveaway is
     * a Telegram Premium giveaway.
     */
    @JsonProperty("is_star_giveaway")
    private boolean starGiveaway;
}
