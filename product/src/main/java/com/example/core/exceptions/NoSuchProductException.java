package com.example.core.exceptions;

public class NoSuchProductException extends RuntimeException{

    public NoSuchProductException (String message) {
        super(message);
    }
}
