package com.rp.course.sec10.helper;

public class ClientError extends RuntimeException {
    public ClientError(String message) {
        super(message);
    }
}
