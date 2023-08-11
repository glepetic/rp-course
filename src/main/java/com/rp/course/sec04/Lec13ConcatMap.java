package com.rp.course.sec04;

import com.rp.course.sec04.helper.OrderService;
import com.rp.course.sec04.helper.UserService;
import com.rp.util.Util;

public class Lec13ConcatMap {

    private static final UserService usersService = UserService.instance();
    private static final OrderService orderService = OrderService.instance();

    public static void main(String[] args) {

        usersService.getUsers()
                // looks the same as flatmap, but waits for one publisher to emit the complete signal before moving on to the next one
                .concatMap(usr -> orderService.getOrders(usr.userId()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }

}
