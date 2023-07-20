package com.rp.scratch.course.sec09.assignment;

import com.rp.scratch.course.sec09.assignment.service.OrderConsumer;
import com.rp.scratch.util.ScratchUtil;

public class ProcessorDemo {

    private static final OrderConsumer orderConsumer = OrderConsumer.instance();

    public static void main(String[] args) {

        orderConsumer
                .processOrders()
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(60);

    }

}
