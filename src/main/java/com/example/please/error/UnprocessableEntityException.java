package com.example.please.error;

public class UnprocessableEntityException extends RuntimeException{
    public UnprocessableEntityException(String message) {
        super(message);
    }

}
