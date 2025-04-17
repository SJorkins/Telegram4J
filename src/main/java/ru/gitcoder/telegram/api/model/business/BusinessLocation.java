package ru.gitcoder.telegram.api.model.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.location.Location;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessLocation {
    /**
     * Address of the business
     */
    @JsonProperty("address")
    private String address;

    /**
     * Optional. Location of the business
     */
    @JsonProperty("location")
    private Location location;
}
