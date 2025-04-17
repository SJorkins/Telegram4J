package ru.gitcoder.telegram.api.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.gitcoder.telegram.api.payload.request.text.MessageOptions;

@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class TelegramRequest<T extends MessageOptions> {
    @JsonProperty("chat_id")
    protected Long chatId;
    @JsonIgnore
    protected String action;
    @JsonUnwrapped
    protected T options;
}
