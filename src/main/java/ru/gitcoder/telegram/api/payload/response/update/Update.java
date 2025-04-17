package ru.gitcoder.telegram.api.payload.response.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.json.MessageDeserializer;
import ru.gitcoder.telegram.api.model.message.Message;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Update {
    @JsonProperty("update_id")
    private Long updateId;
    @JsonProperty("message")
    @JsonDeserialize(using = MessageDeserializer.class)
    private Message message;
}
