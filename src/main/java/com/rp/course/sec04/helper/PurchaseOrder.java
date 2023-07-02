package com.rp.course.sec04.helper;

import com.rp.util.Util;

public record PurchaseOrder(String item, double price, int userId) {

    public static PurchaseOrder create(int userId) {
        return new PurchaseOrder(Util.faker().commerce().productName(), Double.parseDouble(Util.faker().commerce().price()), userId);
    }

}
