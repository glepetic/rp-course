package com.rp.util;

public class FakerDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(Util.faker().name().fullName());
        }

    }

}
