package com.rp.course.sec01.assignment;

import reactor.core.publisher.Mono;

import java.util.Objects;

public class FileService {

    private static FileService instance = null;

    private final FileUtil fileUtil;

    private FileService(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    public static FileService getInstance() {
        return instance = Objects.isNull(instance) ? new FileService(FileUtil.getInstance()) : instance;
    }

    public Mono<String> read(String fileName) {
        return Mono.fromSupplier(() -> this.fileUtil.read(fileName));
    }

    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> this.fileUtil.write(fileName, content));
    }

    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> this.fileUtil.delete(fileName));
    }

}
