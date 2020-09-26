package com.assignment.filesharing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileSharingException extends RuntimeException {
    public MyFileSharingException(String message) {
        super(message);
    }

    public MyFileSharingException(String message, Throwable cause) {
        super(message, cause);
    }
}
