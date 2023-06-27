package com.rp.util;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class Util {

    private static final Faker FAKER = Faker.instance();

    public static Consumer<Object> onNextPrintValue() {
        return o -> System.out.println("Received: " + o);
    }

    public static Consumer<Throwable> onErrorPrintMessage() {
        return err -> System.out.println("ERROR: " + err.getMessage());
    }

    public static Runnable onCompletePrintCompleted() {
        return () -> System.out.println("Completed");
    }

    public static Faker faker() {
        return FAKER;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>();
    }

}
