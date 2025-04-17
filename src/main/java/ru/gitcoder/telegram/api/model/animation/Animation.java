package ru.gitcoder.telegram.api.model.animation;

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
public class Animation {
    /**
     * A set of parameters for working with a media file
     */
    @JsonUnwrapped
    private Multimedia multimedia;

    /**
     * Optional. Animation thumbnail as defined by the sender
     */
    @JsonProperty("thumbnail")
    private Media thumbnail;

    /**
     * Optional. MIME type of the file as defined by the sender
     */
    @JsonProperty("mime_type")
    private String mimeType;
}
