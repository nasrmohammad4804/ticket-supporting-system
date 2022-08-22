package com.nasr.supportingsystemproject.exception;

public class UserNotValidException extends RuntimeException{
    public UserNotValidException(String message) {
        super(message);
    }
}
