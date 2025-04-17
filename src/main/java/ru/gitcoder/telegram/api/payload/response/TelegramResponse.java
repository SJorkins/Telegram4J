package ru.gitcoder.telegram.api.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelegramResponse {
    @JsonProperty("ok")
    private Boolean ok;
    @JsonProperty("error_code")
    private Integer errorCode;

    @JsonProperty("description")
    private String description;
}
