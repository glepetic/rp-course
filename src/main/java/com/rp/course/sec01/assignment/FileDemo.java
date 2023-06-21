package com.rp.course.sec01.assignment;

import com.rp.util.Util;

public class FileDemo {

    private static final FileService fileService = FileService.getInstance();

    public static void main(String[] args) {

        String fileName = "example.txt";

        fileService.write(fileName, "Hello World!")
                .then(fileService.read(fileName))
                .then(fileService.delete(fileName))
                .subscribe(Util.onNextPrintValue(), Util.onErrorPrintMessage(), Util.onCompletePrintCompleted());

        String fileName2 = "file02.txt";

        fileService.write(fileName2, "This is content of file02!")
                .then(fileService.read(fileName2))
                .subscribe(Util.onNextPrintValue(), Util.onErrorPrintMessage(), Util.onCompletePrintCompleted());

    }

}
