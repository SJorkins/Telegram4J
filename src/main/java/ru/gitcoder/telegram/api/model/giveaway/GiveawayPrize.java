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
public class GiveawayPrize {
    /**
     * Optional. The number of Telegram Stars that were split between giveaway winners; for Telegram
     * Star giveaways only
     */
    @JsonProperty("prize_star_count")
    private int starCount;

    /**
     * Optional. The number of months the Telegram Premium subscription won from the giveaway will
     * be active for; for Telegram Premium giveaways only
     */
    @JsonProperty("premium_subscription_month_count")
    private int premiumSubscriptionMonthCount;

    /**
     * Optional. Description of additional giveaway prize
     */
    @JsonProperty("prize_description")
    private String description;
}
