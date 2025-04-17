package ru.gitcoder.telegram.api.model.giveaway;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiveawayStatus {
    /**
     * Optional. Number of undistributed prizes
     */
    @JsonProperty("unclaimed_prize_count")
    private int unclaimedPrizeCount;

    /**
     * Optional. True, if the giveaway was canceled because the payment for it was refunded
     */
    @Getter(AccessLevel.NONE)
    @JsonProperty("was_refunded")
    private boolean wasRefunded;

    public boolean wasRefunded() {
        return wasRefunded;
    }
}
