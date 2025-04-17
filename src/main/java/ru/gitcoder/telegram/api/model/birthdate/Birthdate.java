package ru.gitcoder.telegram.api.model.birthdate;

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
public class Birthdate {
    /**
     * Day of the user's birth; 1-31
     */
    @JsonProperty("day")
    private Integer day;

    /**
     * Month of the user's birth; 1-12
     */
    @JsonProperty("month")
    private Integer month;

    /**
     * Optional. Year of the user's birth
     */
    @JsonProperty("year")
    private Integer year;

    public String asString() {
        return day + "-" + month + "-" + year;
    }
}
