package com.rp.course.sec11.assignment;

public record SlackMessage(String sender,
                          String content) {

    @Override
    public String toString() {
        return String.format("[%s]: %s", this.sender, this.content);
    }

}
