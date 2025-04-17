package ru.gitcoder.telegram.api.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenUtil {

    public String mask(String accessToken) {
        if (accessToken == null || accessToken.length() < 8) return "****";

        return accessToken.substring(0, 4) + "****" + accessToken.substring(accessToken.length() - 4);
    }
}
