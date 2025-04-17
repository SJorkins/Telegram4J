package ru.gitcoder.telegram.api.model.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.TelegramFile;
import ru.gitcoder.telegram.api.model.media.Media;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document {
    @JsonUnwrapped
    private TelegramFile file;

    /**
     * Optional. Document thumbnail as defined by the sender
     */
    @JsonProperty("thumbnail")
    private Media thumbnail;

    /**
     * Optional. MIME type of the file as defined by the sender
     */
    @JsonProperty("mime_type")
    private String mimeType;
}
