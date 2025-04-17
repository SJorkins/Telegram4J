package ru.gitcoder.telegram.api.model.giveaway;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import ru.gitcoder.telegram.api.model.chat.Chat;
import ru.gitcoder.telegram.api.model.country.CountryCode;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Giveaway {
    /**
     * The list of chats which the user must join to participate in the giveaway
     */
    @JsonProperty("chats")
    private List<Chat> chats = new ArrayList<>();

    /**
     * Prize of the giveaway
     */
    @JsonUnwrapped
    private GiveawayPrize prize;

    /**
     * Point in time (Unix timestamp) when winners of the giveaway will be selected
     */
    @JsonProperty("winners_selection_date")
    private long winnersSelectionDate;

    /**
     * The number of users which are supposed to be selected as winners of the giveaway
     */
    @JsonProperty("winner_count")
    private int winnerCount;

    /**
     * Optional. True, if only users who join the chats after the giveaway started should be eligible to
     * win
     */
    @JsonProperty("only_new_members")
    private boolean onlyNewMembers;

    /**
     * Optional. True, if the list of giveaway winners will be visible to everyone
     */
    @Getter(AccessLevel.NONE)
    @JsonProperty("has_public_winners")
    private boolean hasPublicWinners;

    /**
     * Optional. A list of two-letter ISO 3166-1 alpha-2 country codes indicating the countries from
     * which eligible users for the giveaway must come. If empty, then all users can participate in the
     * giveaway. Users with a phone number that was bought on Fragment can always participate in
     * giveaways.
     */
    @JsonProperty("country_codes")
    private List<CountryCode> countries = new ArrayList<>();

    public boolean hasPublicWinners() {
        return hasPublicWinners;
    }
}
