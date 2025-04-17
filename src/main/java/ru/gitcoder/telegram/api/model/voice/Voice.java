package ru.gitcoder.telegram.api.model.voice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.TelegramModel;
import ru.gitcoder.telegram.api.model.media.Multimedia;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Voice implements TelegramModel {
    @JsonUnwrapped
    private Multimedia multimedia;

    /**
     * Optional. MIME type of the file as defined by the sender
     */
    @JsonProperty("mime_type")
    private String mimeType;
}
