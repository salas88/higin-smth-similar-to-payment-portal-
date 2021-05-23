package com.example.please.error;

public class NoDateFoundException extends RuntimeException{
    public NoDateFoundException() {
        super("No data found");
    }
}
