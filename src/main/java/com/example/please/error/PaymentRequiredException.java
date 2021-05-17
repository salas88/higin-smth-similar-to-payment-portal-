package com.example.please.error;

public class PaymentRequiredException extends RuntimeException{
    public PaymentRequiredException(String message) {
        super(message);
    }

    public PaymentRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentRequiredException(Throwable cause) {
        super(cause);
    }
}
