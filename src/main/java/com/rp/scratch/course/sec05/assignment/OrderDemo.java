package com.rp.scratch.course.sec05.assignment;

import com.rp.scratch.util.ScratchUtil;

public class OrderDemo {

    private static final RevenueService revenueService = RevenueService.instance();
    private static final InventoryService inventoryService = InventoryService.instance();

    public static void main(String[] args) {

        revenueService.revenueStream()
                .subscribe(ScratchUtil.subscriber("Revenue"));

        inventoryService.inventoryStream()
                        .subscribe(ScratchUtil.subscriber("Inventory"));

        ScratchUtil.sleepSeconds(60);

    }

}
