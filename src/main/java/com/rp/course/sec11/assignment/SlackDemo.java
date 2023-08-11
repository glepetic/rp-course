package com.rp.course.sec11.assignment;

import com.rp.util.Util;

public class SlackDemo {

    public static void main(String[] args) {

        SlackRoom room = new SlackRoom("Runescape");

        SlackMember member1 = new SlackMember("Gonzalo");
        SlackMember member2 = new SlackMember("Franco");
        SlackMember member3 = new SlackMember("Jorge");

        member1.joinRoom(room);

        Util.sleepSeconds(3);

        member1.sendMessage("Hello world!");

        Util.sleepSeconds(2);

        member2.joinRoom(room);

        Util.sleepSeconds(1);

        member1.sendMessage("Welcome to my channel!");

        Util.sleepSeconds(3);

        member2.sendMessage("Thanks bro");

        Util.sleepSeconds(5);

        member3.joinRoom(room);

        Util.sleepSeconds(1);

        member3.sendMessage("Am I late to the party?");

        Util.sleepSeconds(3);

        member1.sendMessage("Nah man");

        Util.sleepSeconds(2);

        member2.sendMessage("Welcome!");

    }

}
