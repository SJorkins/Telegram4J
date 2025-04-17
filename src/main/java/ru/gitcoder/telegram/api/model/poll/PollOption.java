package ru.gitcoder.telegram.api.model.poll;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.message.MessageEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PollOption {
    /**
     * Option text, 1-100 characters
     */
    @JsonProperty("text")
    private String text;

    /**
     * Optional. Special entities that appear in the option text. Currently, only custom emoji entities are
     * allowed in poll option texts
     */
    @JsonProperty("text_entities")
    private List<MessageEntity> textEntities = new ArrayList<>();

    /**
     * Number of users that voted for this option
     */
    @JsonProperty("voter_count")
    private Integer voterCount;
}
