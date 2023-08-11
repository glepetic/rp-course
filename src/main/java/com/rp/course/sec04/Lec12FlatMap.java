package com.rp.course.sec04;

import com.rp.course.sec04.helper.OrderService;
import com.rp.course.sec04.helper.UserService;
import com.rp.util.Util;

public class Lec12FlatMap {

    private static final UserService usersService = UserService.instance();
    private static final OrderService orderService = OrderService.instance();

    public static void main(String[] args) {

        usersService.getUsers()
                .flatMap(usr -> orderService.getOrders(usr.userId()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }

}
