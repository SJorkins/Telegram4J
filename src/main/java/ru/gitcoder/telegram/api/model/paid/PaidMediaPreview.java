package ru.gitcoder.telegram.api.model.paid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.media.MediaSize;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaidMediaPreview extends PaidMedia {
    /**
     * Size of the media preview
     */
    @JsonUnwrapped
    private MediaSize size;

    /**
     * Optional. Duration of the media in seconds as defined by the sender
     */
    @JsonProperty("duration")
    private int duration;
}
