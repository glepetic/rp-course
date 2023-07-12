package com.rp.scratch.course.sec02;

import com.rp.scratch.course.sec02.helper.NameGenerator;
import com.rp.scratch.util.ScratchUtil;

import java.util.List;

public class Lec07FluxVsList {

    public static void main(String[] args) {

        // takes 5 seconds to get all
        List<String> namesFromList = NameGenerator.getNames(5);
        namesFromList.forEach(System.out::println);

        // get results as they are processed, 1 by 1, 1 per second
        NameGenerator.getNamesReactive(5)
                .subscribe(ScratchUtil.onNextPrintValue());

    }

}
