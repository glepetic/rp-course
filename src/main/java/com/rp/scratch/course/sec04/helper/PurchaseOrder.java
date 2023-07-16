package com.rp.scratch.course.sec04.helper;

import com.rp.scratch.util.ScratchUtil;

public record PurchaseOrder(String item, double price, int userId) {

    public static PurchaseOrder create(int userId) {
        return new PurchaseOrder(ScratchUtil.faker().commerce().productName(), Double.parseDouble(ScratchUtil.faker().commerce().price()), userId);
    }

}
