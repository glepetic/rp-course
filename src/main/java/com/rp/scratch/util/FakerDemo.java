package com.rp.scratch.util;

public class FakerDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(ScratchUtil.faker().name().fullName());
        }

    }

}
