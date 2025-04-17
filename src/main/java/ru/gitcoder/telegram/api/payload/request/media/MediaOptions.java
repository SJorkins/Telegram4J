package ru.gitcoder.telegram.api.payload.request.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.gitcoder.telegram.api.payload.request.text.MessageOptions;

@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaOptions extends MessageOptions {
    @JsonProperty("property")
    private String caption;
    @JsonProperty("has_spoiler")
    private Boolean spoiler;
    @JsonProperty("parse_mode")
    private String parseMode;
}
