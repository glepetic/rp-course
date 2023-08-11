package com.rp.course.sec11.assignment;

import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {

    private final @Getter String name;
    private final Sinks.Many<SlackMessage> sink;

    public SlackRoom(String name) {
        this.name = name;
        this.sink = Sinks.many().multicast().directBestEffort();
    }

    public void sendMessage(SlackMessage message) {
        System.out.println(message.sender() + " sent: " + message.content());
        this.sink.tryEmitNext(message);
    }

    public Flux<SlackMessage> getMessages() {
        return this.sink.asFlux();
    }

}
