package com.rp.course.sec12;

import com.rp.course.sec12.helper.rateLimit.BookService;
import com.rp.course.sec12.helper.rateLimit.UserCategoryService;
import com.rp.util.Util;

public class Lec02CtxRateLimiterDemo {

    private static final BookService bookService = BookService.instance();
    private static final UserCategoryService userService = UserCategoryService.instance();

    public static void main(String[] args) {

        // Standard user (can get 2 books) -> throws exception on 3rd
        bookService.getBook()
                .repeat(2)
                .contextWrite(userService.context())
                .contextWrite(ctx -> ctx.put("user", "sam"))
                .subscribe(Util.subscriber("sam"));

        // Premium user (can get 3 books)
        bookService.getBook()
                .repeat(2)
                .contextWrite(userService.context())
                .contextWrite(ctx -> ctx.put("user", "paul"))
                .subscribe(Util.subscriber("paul"));

    }

}
