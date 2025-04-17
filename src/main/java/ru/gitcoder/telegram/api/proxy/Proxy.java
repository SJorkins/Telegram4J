package ru.gitcoder.telegram.api.proxy;

import ru.gitcoder.telegram.api.utility.NullableObject;

public interface Proxy extends NullableObject {
    String getHost();

    Integer getPort();

    String getUsername();

    String getPassword();
}
