package ru.gitcoder.telegram.api.model.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode()
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaSize {
    /**
     * Width as defined by the sender
     */
    @JsonProperty("width")
    private int width;

    /**
     * Height as defined by the sender
     */
    @JsonProperty("height")
    private int height;
}
