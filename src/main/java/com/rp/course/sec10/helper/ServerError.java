package com.rp.course.sec10.helper;

public class ServerError extends RuntimeException {
    public ServerError(String message) {
        super(message);
    }
}
