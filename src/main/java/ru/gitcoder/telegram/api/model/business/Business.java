package ru.gitcoder.telegram.api.model.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.sticker.Sticker;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Business {
    /**
     * Optional. Title text of the business intro
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. Message text of the business intro
     */
    @JsonProperty("message")
    private String message;

    /**
     * Optional. Sticker of the business intro
     */
    @JsonProperty("sticker")
    private Sticker sticker;
}
