package com.rp.scratch.course.sec12;

import com.rp.scratch.course.sec12.helper.Welcome;
import com.rp.scratch.util.ScratchUtil;
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
                .subscribe(ScratchUtil.subscriber("NoContextSub"));

        welcome.getWelcomeMessage()
                .contextWrite(Context.of("user", "sam"))
                .subscribe(ScratchUtil.subscriber("ContextSub"));

        welcome.getWelcomeMessageWithDefault()
                .subscribe(ScratchUtil.subscriber("NoContextSub2"));

        welcome.getWelcomeMessageWithDefault()
                // update with UnaryOperator<Context> interface
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "paul"))
                .subscribe(ScratchUtil.subscriber("ContextSub2"));

    }

}
