package com.rp.course.sec03.assignment.solution1;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

public class S1FileReaderService {

    private static S1FileReaderService instance = null;

    private final S1FileReaderUtil fileReaderUtil;

    private S1FileReaderService(S1FileReaderUtil fileReaderUtil) {
        this.fileReaderUtil = fileReaderUtil;
    }

    public static S1FileReaderService instance() {
        return instance = Objects.isNull(instance) ? new S1FileReaderService(S1FileReaderUtil.instance()) : instance;
    }

    public Flux<String> readFileContent(String fileName) {
        // This solution loads the whole file content lines into memory so it's less efficient
        List<String> lines;
        try {
            lines = this.fileReaderUtil.readLines(fileName);
        } catch(Exception e) {
            // this is done so the subscriber gets notified about the error
            return Flux.error(e);
        }
        return Flux.generate(
                () -> 0,
                (lineNumber, sink) -> {
                    String line = lines.get(lineNumber);
                    System.out.println("Emitting: " + line);
                    sink.next(line);
                    if (lineNumber + 1 == lines.size()) sink.complete();
                    return lineNumber + 1;
                }
        );
    }

}
