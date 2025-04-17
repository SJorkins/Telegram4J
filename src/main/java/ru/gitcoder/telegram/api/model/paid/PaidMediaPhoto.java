package ru.gitcoder.telegram.api.model.paid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.gitcoder.telegram.api.model.media.Media;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaidMediaPhoto extends PaidMedia {
    /**
     * The photo
     */
    @JsonProperty("photo")
    private List<Media> photos = new ArrayList<>();
}
