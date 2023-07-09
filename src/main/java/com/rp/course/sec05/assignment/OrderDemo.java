package com.rp.course.sec05.assignment;

import com.rp.util.Util;

public class OrderDemo {

    private static final RevenueService revenueService = RevenueService.instance();
    private static final InventoryService inventoryService = InventoryService.instance();

    public static void main(String[] args) {

        revenueService.revenueStream()
                .subscribe(Util.subscriber("Revenue"));

        inventoryService.inventoryStream()
                        .subscribe(Util.subscriber("Inventory"));

        Util.sleepSeconds(60);

    }

}
