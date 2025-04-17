package ru.gitcoder.telegram.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements TelegramModel {
    /**
     * Unique identifier for this user or bot. This number may have more than 32 significant bits and
     * some programming languages may have difficulty/silent defects in interpreting it. But it has at
     * most 52 significant bits, so a 64-bit integer or double-precision float type are safe for storing this
     * identifier.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * Optional. information about the user or bot
     */
    @JsonUnwrapped
    private UserAbout info;

    /**
     * User's or bot's first name
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Optional. User's or bot's last name
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Optional. User's or bot's username
     */
    @JsonProperty("username")
    private String username;

    /**
     * Optional. IETF language tag of the user's language
     */
    @JsonProperty("language_code")
    private String languageCode;
}
