package ru.gitcoder.telegram.api.payload.request.text;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextOptions extends MessageOptions {
    @JsonProperty("has_spoiler")
    private Boolean spoiler;
    @JsonProperty("parse_mode")
    private String parseMode;
}
