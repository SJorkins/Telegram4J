package ru.gitcoder.telegram.api.model.contact;

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
public class Contact {
    /**
     * Contact's phone number
     */
    @JsonProperty("phone_number")
    private String phoneNumber;

    /**
     * Contact's first name
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Optional. Contact's last name
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * 	Optional. Contact's user identifier in Telegram. This number may have more than 32 significant
     * 	bits and some programming languages may have difficulty/silent defects in interpreting it. But it
     * 	has at most 52 significant bits, so a 64-bit integer or double-precision float type are safe for
     * 	storing this identifier.
     */
    @JsonProperty("user_id")
    private int userId;

    /**
     * Optional. Additional data about the contact in the form of a <a href="https://en.wikipedia.org/wiki/VCard">...</a>
     */
    @JsonProperty("vcard")
    private String vcard;
}
