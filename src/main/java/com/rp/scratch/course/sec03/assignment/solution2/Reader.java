package com.rp.scratch.course.sec03.assignment.solution2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Reader {

    private static final Path PATH = Paths.get("src/main/resources/assignment/sec03");

    private final BufferedReader reader;
    private boolean open;

    private Reader(BufferedReader reader) {
        this.reader = reader;
        this.open = true;
    }

    public static Reader of(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PATH.resolve(fileName).toFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new Reader(reader);
    }

    public String nextLine() {
        try {
            String line = this.reader.readLine();
            if (Objects.isNull(line)) this.reader.close();
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            if (this.open) {
                System.out.println("Closing reader");
                this.reader.close();
                this.open = false;
            } else System.out.println("Reader was already closed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
