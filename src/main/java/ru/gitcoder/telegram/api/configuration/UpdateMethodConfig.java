package ru.gitcoder.telegram.api.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.gitcoder.telegram.api.update.UpdateMethod;
import ru.gitcoder.telegram.api.utility.NullableObject;

@Getter
@RequiredArgsConstructor
public abstract class UpdateMethodConfig implements NullableObject {
    private final UpdateMethod method;
}
