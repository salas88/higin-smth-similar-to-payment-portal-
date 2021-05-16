package com.example.please.error;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(Throwable cause) {
        super(cause);
    }

}
