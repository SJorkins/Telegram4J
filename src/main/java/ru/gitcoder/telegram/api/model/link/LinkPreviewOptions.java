package ru.gitcoder.telegram.api.model.link;

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
public class LinkPreviewOptions {
    /**
     * Optional. True, if the link preview is disabled
     */
    @JsonProperty("is_disabled")
    private boolean disabled;

    /**
     * Optional. URL to use for the link preview. If empty, then the first URL found in the message text
     * will be used
     */
    @JsonProperty("url")
    private String url;

    /**
     * Optional. True, if the media in the link preview is supposed to be shrunk; ignored if the URL isn't
     * explicitly specified or media size change isn't supported for the preview
     */
    @JsonProperty("prefer_small_media")
    private boolean preferSmallMedia;

    /**
     * Optional. True, if the media in the link preview is supposed to be enlarged; ignored if the URL
     * isn't explicitly specified or media size change isn't supported for the preview
     */
    @JsonProperty("prefer_large_media")
    private boolean preferLargeMedia;

    /**
     * Optional. True, if the link preview must be shown above the message text; otherwise, the link
     * preview will be shown below the message text
     */
    @JsonProperty("show_above_text")
    private boolean showAboveText;
}
