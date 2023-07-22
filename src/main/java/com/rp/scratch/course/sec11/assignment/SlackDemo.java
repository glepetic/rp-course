package com.rp.scratch.course.sec11.assignment;

import com.rp.scratch.util.ScratchUtil;

public class SlackDemo {

    public static void main(String[] args) {

        SlackRoom room = new SlackRoom("Runescape");

        SlackMember member1 = new SlackMember("Gonzalo");
        SlackMember member2 = new SlackMember("Franco");
        SlackMember member3 = new SlackMember("Jorge");

        member1.joinRoom(room);

        ScratchUtil.sleepSeconds(3);

        member1.sendMessage("Hello world!");

        ScratchUtil.sleepSeconds(2);

        member2.joinRoom(room);

        ScratchUtil.sleepSeconds(1);

        member1.sendMessage("Welcome to my channel!");

        ScratchUtil.sleepSeconds(3);

        member2.sendMessage("Thanks bro");

        ScratchUtil.sleepSeconds(5);

        member3.joinRoom(room);

        ScratchUtil.sleepSeconds(1);

        member3.sendMessage("Am I late to the party?");

        ScratchUtil.sleepSeconds(3);

        member1.sendMessage("Nah man");

        ScratchUtil.sleepSeconds(2);

        member2.sendMessage("Welcome!");

    }

}
