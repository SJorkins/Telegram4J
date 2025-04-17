package ru.gitcoder.telegram.api.model.location;

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
public class Location {
    /**
     * Latitude as defined by the sender
     */
    @JsonProperty("latitude")
    private Float latitude;

    /**
     * 	Longitude as defined by the sender
     */
    @JsonProperty("longitude")
    private Float longitude;

    /**
     * Optional. The radius of uncertainty for the location, measured in meters; 0-1500
     */
    @JsonProperty("horizontal_accuracy")
    private Float horizontal_accuracy;

    /**
     * Optional. Time relative to the message sending date, during which the location can be updated; in
     * seconds. For active live locations only.
     */
    @JsonProperty("live_period")
    private Integer livePeriod;

    /**
     * Optional. The direction in which user is moving, in degrees; 1-360. For active live locations only.
     */
    @JsonProperty("heading")
    private Integer heading;

    /**
     * 	Optional. The maximum distance for proximity alerts about approaching another chat member, in
     * 	meters. For sent live locations only.
     */
    @JsonProperty("proximity_alert_radius")
    private Integer proximityAlertRadius;
}
