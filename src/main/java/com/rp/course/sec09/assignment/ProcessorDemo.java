package com.rp.course.sec09.assignment;

import com.rp.course.sec09.assignment.service.OrderConsumer;
import com.rp.util.Util;

public class ProcessorDemo {

    private static final OrderConsumer orderConsumer = OrderConsumer.instance();

    public static void main(String[] args) {

        orderConsumer
                .processOrders()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

}
