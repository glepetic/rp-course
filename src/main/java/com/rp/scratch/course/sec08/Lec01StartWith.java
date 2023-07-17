package com.rp.scratch.course.sec08;

import com.rp.scratch.course.sec08.helper.NameGenerator;
import com.rp.scratch.util.ScratchUtil;

public class Lec01StartWith {

    public static void main(String[] args) {

        NameGenerator.instance()
                .generateNames()
                .take(2)
                .subscribe(ScratchUtil.subscriber("Sam"));

        NameGenerator.instance()
                .generateNames()
                .take(2)
                .subscribe(ScratchUtil.subscriber("Mike"));

        NameGenerator.instance()
                .generateNames()
                .take(3)
                .subscribe(ScratchUtil.subscriber("Jake"));

        NameGenerator.instance()
                .generateNames()
                .filter(name -> name.startsWith("A"))
                .take(1)
                .subscribe(ScratchUtil.subscriber("Jake"));

        ScratchUtil.sleepSeconds(10);

    }

}
