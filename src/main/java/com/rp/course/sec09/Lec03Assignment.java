package com.rp.course.sec09;

import com.rp.course.sec09.helper.bookstore.Accountant;
import com.rp.util.Util;

public class Lec03Assignment {

    private static final Accountant accountant = Accountant.instance();

    public static void main(String[] args) {

        accountant.getRevenueReport()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

}
