package ru.gitcoder.telegram.api.model.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.TelegramFile;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Media {
    /**
     * Size of the media
     */
    @JsonUnwrapped
    private MediaSize size;

    /**
     * File of media
     */
    @JsonUnwrapped
    private TelegramFile file;
}
