package com.rp.scratch.course.sec01.assignment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileUtil {

    private static final Path PATH = Paths.get("src/main/resources/assignment/sec01");

    private static FileUtil instance = null;

    private FileUtil() {
    }

    public static FileUtil getInstance() {
        return instance = Objects.isNull(instance) ? new FileUtil() : instance;
    }

    public String read(String fileName) {
        try {
            String content = Files.readString(PATH.resolve(fileName));
            System.out.println("Read file successfully");
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String fileName, String content) {
        try {
            Files.writeString(PATH.resolve(fileName), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Wrote file successfully");

    }

    public void delete(String fileName) {
        try {
            Files.delete(PATH.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Deleted file successfully");
    }
}
