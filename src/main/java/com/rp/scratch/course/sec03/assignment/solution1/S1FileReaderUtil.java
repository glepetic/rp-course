package com.rp.scratch.course.sec03.assignment.solution1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class S1FileReaderUtil {

    private static final Path PATH = Paths.get("src/main/resources/scratch/assignment/sec03");

    private static S1FileReaderUtil instance = null;

    private S1FileReaderUtil() {
    }

    public static S1FileReaderUtil instance() {
        return instance = Objects.isNull(instance) ? new S1FileReaderUtil() : instance;
    }

    public List<String> readLines(String fileName) {
        try {
            List<String> lines = Files.readAllLines(PATH.resolve(fileName));
            System.out.println("Read file successfully");
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
