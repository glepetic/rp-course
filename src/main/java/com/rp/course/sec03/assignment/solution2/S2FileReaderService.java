package com.rp.course.sec03.assignment.solution2;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.Objects;
import java.util.Optional;

public class S2FileReaderService {

    private static S2FileReaderService instance = null;

    private S2FileReaderService() {
    }

    public static S2FileReaderService instance() {
        return instance = Objects.isNull(instance) ? new S2FileReaderService() : instance;
    }

    public Flux<String> readFileContent(String fileName) {
        // This solution only loads lines as they are requested from the Reader object which makes it more efficient
        return Flux.generate(() -> Reader.of(fileName), this::readNext, Reader::close);
    }

    private Reader readNext(Reader reader, SynchronousSink<String> lineSink) {
        Optional.ofNullable(reader.nextLine())
                .ifPresentOrElse(line -> this.emit(line, lineSink), () -> this.complete(reader, lineSink));
        return reader;
    }

    private void emit(String line, SynchronousSink<String> lineSink) {
        System.out.println("Emitting: " + line);
        lineSink.next(line);
    }

    private void complete(Reader reader, SynchronousSink<String> lineSink) {
        reader.close();
        lineSink.complete();
    }

}
