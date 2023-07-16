package com.rp.scratch.course.sec05.assignment;

import com.rp.scratch.util.ScratchUtil;

public record APurchaseOrder(String item, double price, Integer quantity) {

    public static APurchaseOrder create(String product) {
        return new APurchaseOrder(product,
                Double.parseDouble(ScratchUtil.faker().commerce().price()),
                ScratchUtil.faker().random().nextInt(1, 10)
        );
    }

}
