package com.rp.scratch.course.sec01.assignment;

import com.rp.scratch.util.ScratchUtil;

public class FileDemo {

    private static final FileService fileService = FileService.instance();

    public static void main(String[] args) {

        String fileName = "example.txt";

        fileService.write(fileName, "Hello World!")
                .then(fileService.read(fileName))
                .then(fileService.delete(fileName))
                .subscribe(ScratchUtil.onNextPrintValue(), ScratchUtil.onErrorPrintMessage(), ScratchUtil.onCompletePrintCompleted());

        String fileName2 = "file02.txt";

        fileService.write(fileName2, "This is content of file02!")
                .then(fileService.read(fileName2))
                .subscribe(ScratchUtil.onNextPrintValue(), ScratchUtil.onErrorPrintMessage(), ScratchUtil.onCompletePrintCompleted());

    }

}
