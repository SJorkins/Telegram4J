package ru.gitcoder.telegram.api.model.paid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.video.Video;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaidMediaVideo extends PaidMedia {
    /**
     * The video
     */
    @JsonUnwrapped
    private Video video;
}
