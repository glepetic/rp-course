package com.rp.scratch.course.sec10;

import com.rp.scratch.course.sec10.helper.ClientError;
import com.rp.scratch.course.sec10.helper.ServerError;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec04RetryWhenAdvanced {

    public static void main(String[] args) {

        orderService(ScratchUtil.faker().business().creditCardNumber())
                .doOnError(err -> System.out.println(err.getMessage()))
                .retryWhen(Retry.from(fluxRetry -> fluxRetry
                        .doOnNext(rs -> System.out.println("Retries count: " + rs.totalRetries()))
                        .handle((rs, sink) -> {
                            // if the Retry sees some onNext signal, it assumes it has to retry
                            if (rs.failure() instanceof ServerError) sink.next(1);
                            // if the Retry sees some onError signal, it passes it down to the downstream (does not retry)
                            else if (rs.failure() instanceof ClientError) sink.error(rs.failure());
                        })
                        .delayElements(Duration.ofSeconds(1))))
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(60);

    }

    private static Mono<String> orderService(String creditCardNumber) {
        return Mono.fromSupplier(() -> {
            processPayment(creditCardNumber);
            return "Payment id -> " + ScratchUtil.faker().idNumber().valid();
        });
    }

    // payment service
    private static void processPayment(String creditCardNumber) {
        System.out.println("Processing payment for creditCardNumber: " + creditCardNumber);
        double random = ScratchUtil.faker().random().nextDouble();
        if (random < 0.3) throw new ServerError("Oops 500! Maybe try reprocessing");
        else if (random < 0.6) throw new ClientError("Oops 400! Invalid payment");
    }

}
