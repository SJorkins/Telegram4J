package ru.gitcoder.telegram.api.configuration;

public class NoUpdateMethodConfig extends UpdateMethodConfig {

    public NoUpdateMethodConfig() {
        super(null);
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
