package com.rp.scratch.course.sec04;

import com.rp.scratch.course.sec04.helper.OrderService;
import com.rp.scratch.course.sec04.helper.UserService;
import com.rp.scratch.util.ScratchUtil;

public class Lec13ConcatMap {

    private static final UserService usersService = UserService.instance();
    private static final OrderService orderService = OrderService.instance();

    public static void main(String[] args) {

        usersService.getUsers()
                // looks the same as flatmap, but waits for one publisher to emit the complete signal before moving on to the next one
                .concatMap(usr -> orderService.getOrders(usr.userId()))
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(10);

    }

}
