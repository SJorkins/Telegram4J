package ru.gitcoder.telegram.api.model.game;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.animation.Animation;
import ru.gitcoder.telegram.api.model.media.Media;
import ru.gitcoder.telegram.api.model.message.MessageEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Game {
    /**
     * Title of the game
     */
    @JsonProperty("title")
    private String title;

    /**
     * Description of the game
     */
    @JsonProperty("description")
    private String description;

    /**
     * Photo that will be displayed in the game message in chats
     */
    @JsonProperty("photo")
    private List<Media> photos = new ArrayList<>();

    /**
     * Optional. Brief description of the game or high scores included in the game message. Can
     * be automatically edited to include current high scores for the game when the bot calls
     * setGameScore, or manually edited using editMessageText. 0-4096 characters.
     */
    @JsonProperty("text")
    private String text;

    /**
     * Optional. Special entities that appear in text, such as usernames, URLs, bot commands, etc.
     */
    @JsonProperty("text_entities")
    private List<MessageEntity> textEntities;

    /**
     * Optional. Animation that will be displayed in the game message in chats. Upload via BotFather
     */
    @JsonProperty("animation")
    private Animation animation;
}
