package com.rp.course.sec08;

import com.rp.course.sec08.helper.NameGenerator;
import com.rp.util.Util;

public class Lec01StartWith {

    public static void main(String[] args) {

        NameGenerator.instance()
                .generateNames()
                .take(2)
                .subscribe(Util.subscriber("Sam"));

        NameGenerator.instance()
                .generateNames()
                .take(2)
                .subscribe(Util.subscriber("Mike"));

        NameGenerator.instance()
                .generateNames()
                .take(3)
                .subscribe(Util.subscriber("Jake"));

        NameGenerator.instance()
                .generateNames()
                .filter(name -> name.startsWith("A"))
                .take(1)
                .subscribe(Util.subscriber("Jake"));

        Util.sleepSeconds(10);

    }

}
