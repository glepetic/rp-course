package com.rp.assignment.sec01;

import com.rp.util.Util;

public class FileDemo {

    private static FileService fileService = FileService.getInstance();

    public static void main(String[] args) {

        String fileName = "/assignment/sec01/example.txt";

        fileService.write(fileName, "Hello World!")
                .subscribe(
                        Util.onNextPrintValue(),
                        Util.onErrorPrintMessage(),
                        () -> fileService.read(fileName)
                                .subscribe(Util.onNextPrintValue(),
                                        Util.onErrorPrintMessage(),
                                        () -> fileService.delete(fileName)
                                                .subscribe(Util.onNextPrintValue(),
                                                        Util.onErrorPrintMessage(),
                                                        Util.onCompletePrintCompleted()))
                );

    }

}
