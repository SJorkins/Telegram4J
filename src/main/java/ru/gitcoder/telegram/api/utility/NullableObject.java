package ru.gitcoder.telegram.api.utility;

public interface NullableObject {

    default boolean isNull() {
        return false;
    }
}
