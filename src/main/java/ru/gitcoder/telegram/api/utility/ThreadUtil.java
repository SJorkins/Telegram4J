package ru.gitcoder.telegram.api.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadFactory;

@UtilityClass
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadUtil {

    public ThreadFactory createThreadFactory(String threadName, boolean daemon) {
        return runnable -> {
            Thread thread = new Thread(runnable, threadName);
            thread.setDaemon(daemon);
            return thread;
        };
    }
}
