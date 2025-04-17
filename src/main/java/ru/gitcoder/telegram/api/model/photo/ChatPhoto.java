package ru.gitcoder.telegram.api.model.photo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatPhoto {
    /**
     * File identifier of small (160x160) chat photo. This file_id can be used only for photo download and
     * only for as long as the photo is not changed.
     */
    @JsonProperty("small_file_id")
    private String smallFileId;

    /**
     * Unique file identifier of small (160x160) chat photo, which is supposed to be the same over time
     * and for different bots. Can't be used to download or reuse the file.
     */
    @JsonProperty("small_file_unique_id")
    private String smallFileUniqueId;

    /**
     * File identifier of big (640x640) chat photo. This file_id can be used only for photo download and
     * only for as long as the photo is not changed.
     */
    @JsonProperty("big_file_id")
    private String bigFileId;

    /**
     * Unique file identifier of big (640x640) chat photo, which is supposed to be the same over time
     * and for different bots. Can't be used to download or reuse the file.
     */
    @JsonProperty("big_file_unique_id")
    private String bigFileUniqueId;
}
