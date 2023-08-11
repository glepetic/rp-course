package com.rp.course.sec03.assignment;

import com.rp.course.sec03.assignment.solution1.S1FileReaderService;
import com.rp.course.sec03.assignment.solution2.S2FileReaderService;
import com.rp.util.Util;

public class FileReaderDemo {

    private static final S1FileReaderService solution1 = S1FileReaderService.instance();
    private static final S2FileReaderService solution2 = S2FileReaderService.instance();

    public static void main(String[] args) {

        String fileName1 = "demo.txt";
//        String fileName2 = "nonexistent.txt";

        // Using solution 1 -> whole file loaded into memory
        solution1.readFileContent(fileName1)
                .take(5)
                .subscribe(Util.subscriber());

        // Using solution 2 -> only loads the lines that are requested by the subscriber
        solution2.readFileContent(fileName1)
                .take(5)
                .subscribe(Util.subscriber());

    }

}
