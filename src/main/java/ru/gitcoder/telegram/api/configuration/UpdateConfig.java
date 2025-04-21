package ru.gitcoder.telegram.api.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.gitcoder.telegram.api.update.UpdateType;
import ru.gitcoder.telegram.api.utility.NullableObject;

@Getter
@RequiredArgsConstructor
public abstract class UpdateConfig implements NullableObject {
    private final UpdateType type;
}
