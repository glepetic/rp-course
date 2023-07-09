package com.rp.course.sec05.assignment;

import com.rp.util.Util;

public record APurchaseOrder(String item, double price, Integer quantity) {

    public static APurchaseOrder create(String product) {
        return new APurchaseOrder(product,
                Double.parseDouble(Util.faker().commerce().price()),
                Util.faker().random().nextInt(1, 10)
        );
    }

}
