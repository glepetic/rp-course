package com.rp.scratch.util;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class ScratchUtil {

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
        sleepMillis(1000*seconds);
    }

    public static void sleepMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>();
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }

}
