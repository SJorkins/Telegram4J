package ru.gitcoder.telegram.api.model.venue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.TelegramModel;
import ru.gitcoder.telegram.api.model.location.Location;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Venue implements TelegramModel {
    /**
     * Venue location. Can't be a live location
     */
    @JsonProperty("location")
    private Location location;

    /**
     * Name of the venue
     */
    @JsonProperty("title")
    private String title;

    /**
     * Address of the venue
     */
    @JsonProperty("address")
    private String address;

    /**
     * Optional. Foursquare identifier of the venue
     */
    @JsonProperty("foursquare_id")
    private String foursquareId;

    /**
     * Optional. Foursquare type of the venue. (For example, “arts_entertainment/default”,
     * “arts_entertainment/aquarium” or “food/icecream”.)
     */
    @JsonProperty("foursquare_type")
    private String foursquareType;

    /**
     * Optional. Google Places identifier of the venue
     */
    @JsonProperty("google_place_id")
    private String googlePlaceId;

    /**
     * Optional. Google Places type of the venue. (See supported types.)
     */
    @JsonProperty("google_place_type")
    private GooglePlace googlePlace;
}
