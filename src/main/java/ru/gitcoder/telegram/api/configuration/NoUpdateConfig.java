package ru.gitcoder.telegram.api.configuration;

public class NoUpdateConfig extends UpdateConfig {

    public NoUpdateConfig() {
        super(null);
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
