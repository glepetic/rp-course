package com.rp.scratch.course.sec10.helper;

public class ClientError extends RuntimeException {
    public ClientError(String message) {
        super(message);
    }
}
