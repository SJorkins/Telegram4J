package ru.gitcoder.telegram.api.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@UtilityClass
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {
    private static final Map<Path, ReentrantLock> fileLocks = new ConcurrentHashMap<>();

    public void writeToFile(@NotNull Path path, @NotNull String content, @NotNull Charset charset, boolean append) {
        ReentrantLock lock = fileLocks.computeIfAbsent(path, p -> new ReentrantLock());
        lock.lock();
        try {
            writeContent(path, content, charset, append);
        } finally {
            lock.unlock();
        }
    }

    public void writeToFile(@NotNull Path path, @NotNull String content, boolean append) {
        writeToFile(path, content, StandardCharsets.UTF_8, append);
    }

    public CompletableFuture<Void> writeToFileAsync(@NotNull Path path, @NotNull String content, @NotNull Charset charset, boolean append) {
        return CompletableFuture.runAsync(() ->
                writeToFile(path, content, charset, append));
    }

    public CompletableFuture<Void> writeToFileAsync(@NotNull Path path, @NotNull String content, boolean append) {
        return writeToFileAsync(path, content, StandardCharsets.UTF_8, append);
    }

    private void writeContent(@NotNull Path path, @NotNull String content, @NotNull Charset charset, boolean append) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset,
                StandardOpenOption.CREATE, append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to write to file", e);
        }
    }
}
