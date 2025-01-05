package com.myjavacrudapp.exceptions;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}