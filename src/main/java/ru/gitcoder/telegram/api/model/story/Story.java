package ru.gitcoder.telegram.api.model.story;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.TelegramModel;
import ru.gitcoder.telegram.api.model.chat.Chat;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Story implements TelegramModel {
    /**
     * Chat that posted the story
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Unique identifier for the story in the chat
     */
    @JsonProperty("id")
    private Integer id;
}
