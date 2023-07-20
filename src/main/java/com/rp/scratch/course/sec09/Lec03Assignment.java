package com.rp.scratch.course.sec09;

import com.rp.scratch.course.sec09.helper.bookstore.Accountant;
import com.rp.scratch.util.ScratchUtil;

public class Lec03Assignment {

    private static final Accountant accountant = Accountant.instance();

    public static void main(String[] args) {

        accountant.getRevenueReport()
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(60);

    }

}
