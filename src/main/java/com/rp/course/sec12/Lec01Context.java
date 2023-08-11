package com.rp.course.sec12;

import com.rp.course.sec12.helper.Welcome;
import com.rp.util.Util;
import reactor.util.context.Context;

public class Lec01Context {

    private static final Welcome welcome = Welcome.instance();

    public static void main(String[] args) {

        /*
            context is passed upstream (from subscriber to publisher)
            several keys can be added, if the same key gets written twice, last value goes
            (last value means closest -upwards- to the method who consumes the context)
         */
        welcome.getWelcomeMessage()
                .subscribe(Util.subscriber("NoContextSub"));

        welcome.getWelcomeMessage()
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber("ContextSub"));

        welcome.getWelcomeMessageWithDefault()
                .subscribe(Util.subscriber("NoContextSub2"));

        welcome.getWelcomeMessageWithDefault()
                // update with UnaryOperator<Context> interface
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "paul"))
                .subscribe(Util.subscriber("ContextSub2"));

    }

}
