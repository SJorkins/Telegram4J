package ru.gitcoder.telegram.api.model.paid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaidMediaInfo {
    /**
     * The number of Telegram Stars that must be paid to buy access to the media
     */
    @JsonProperty("start_count")
    private int startCount;

    /**
     * Information about the paid media
     */
    @JsonProperty("paid_media")
    private PaidMedia[] information;
}
