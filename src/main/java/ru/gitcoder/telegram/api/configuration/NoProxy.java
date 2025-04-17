package ru.gitcoder.telegram.api.configuration;

import ru.gitcoder.telegram.api.proxy.Proxy;

public class NoProxy implements Proxy {

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public Integer getPort() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
