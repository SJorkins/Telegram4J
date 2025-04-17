package ru.gitcoder.telegram.api.model.audio;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.media.Multimedia;
import ru.gitcoder.telegram.api.model.media.Media;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Audio {
    /**
     * A set of parameters for working with a media file
     */
    @JsonUnwrapped
    private Multimedia multimedia;

    /**
     * Optional. Performer of the audio as defined by the sender or by audio tags
     */
    @JsonProperty("performer")
    private String performer;

    /**
     * Optional. Title of the audio as defined by the sender or by audio tags
     */
    @JsonProperty("title")
    private String title;

    /**
     * Optional. MIME type of the file as defined by the sender
     */
    @JsonProperty("mime_type")
    private String mimeType;

    /**
     * Optional. Thumbnail of the album cover to which the music file belongs
     */
    @JsonProperty("thumbnail")
    private Media thumbnail;
}
