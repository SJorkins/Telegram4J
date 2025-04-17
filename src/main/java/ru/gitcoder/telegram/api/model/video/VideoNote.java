package ru.gitcoder.telegram.api.model.video;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.TelegramModel;
import ru.gitcoder.telegram.api.model.media.Media;
import ru.gitcoder.telegram.api.model.media.Multimedia;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoNote implements TelegramModel {
    @JsonUnwrapped
    private Multimedia multimedia;

    /**
     * Video width and height (diameter of the video message) as defined by the sender
     */
    @JsonProperty("length")
    private Long length;

    /**
     * Optional. Video thumbnail
     */
    @JsonProperty("thumbnail")
    private Media thumbnail;
}
