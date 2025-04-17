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

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Video implements TelegramModel {
    @JsonUnwrapped
    private Multimedia multimedia;

    /**
     * Optional. Video thumbnail
     */
    @JsonProperty("thumbnail")
    private Media thumbnail;

    /**
     * Optional. Available sizes of the cover of the video in the message
     */
    @JsonProperty("cover")
    private List<Media> covers = new ArrayList<>();

    /**
     * Optional. Timestamp in seconds from which the video will play in the message
     */
    @JsonProperty("start_timestamp")
    private Long startTimeStamp;

    /**
     * Optional. MIME type of the file as defined by the sender
     */
    @JsonProperty("mime_type")
    private String mimeType;
}
