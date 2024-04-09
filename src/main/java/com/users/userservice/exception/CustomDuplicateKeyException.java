package com.users.userservice.exception;


public class CustomDuplicateKeyException extends RuntimeException {
    public CustomDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
