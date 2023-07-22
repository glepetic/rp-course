package com.rp.scratch.course.sec11.assignment;

import lombok.Getter;

public class SlackMember {

    private final @Getter String name;
    private SlackRoom room;

    public SlackMember(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        this.room.sendMessage(new SlackMessage(this.name, message));
    }

    public void joinRoom(SlackRoom room) {
        System.out.println("<< " + this.name + " has joined " + room.getName() + " >>");
        this.room = room;
        this.room.getMessages()
                .filter(message -> !this.name.equals(message.sender()))
                .subscribe(message -> System.out.println("<< " + this.name + " received >> " + message));
    }

}
