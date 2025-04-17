package ru.gitcoder.telegram.api.payload.request.text;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.gitcoder.telegram.api.payload.request.TelegramRequest;

import java.util.Objects;

@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextRequest extends TelegramRequest<TextOptions> {
    @JsonProperty("text")
    private String text;
}
