package ru.gitcoder.telegram.api.model.message.forward;

import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class ForwardProcessor {
    private final ForwardInfo origin;

    public ForwardProcessor user(Consumer<ForwardInfo.User> action) {
        if (validateForward(ForwardType.USER)) {
            action.accept((ForwardInfo.User) origin);
        }
        return this;
    }

    public ForwardProcessor anonymous(Consumer<ForwardInfo.HiddenUser> action) {
        if (validateForward(ForwardType.HIDDEN_USER)) {
            action.accept((ForwardInfo.HiddenUser) origin);
        }
        return this;
    }

    public ForwardProcessor chat(Consumer<ForwardInfo.Chat> action) {
        if (validateForward(ForwardType.CHAT)) {
            action.accept((ForwardInfo.Chat) origin);
        }
        return this;
    }

    public ForwardProcessor channel(Consumer<ForwardInfo.Channel> action) {
        if (validateForward(ForwardType.CHANNEL)) {
            action.accept((ForwardInfo.Channel) origin);
        }
        return this;
    }

    public ForwardProcessor when(ForwardType type, Consumer<ForwardInfo> action) {
        if (origin.getType() == type && action != null) {
            action.accept(origin);
        }
        return this;
    }

    public void otherwise(Runnable action) {
        if (action != null && validateForward(ForwardType.UNKNOWN)) {
            action.run();
        }
    }

    private boolean validateForward(ForwardType forwardType) {
        return origin != null && origin.getType().equals(forwardType);
    }
}
