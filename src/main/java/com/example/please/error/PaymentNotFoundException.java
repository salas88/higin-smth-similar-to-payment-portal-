package com.example.please.error;

public class PaymentNotFoundException extends RuntimeException{

    public PaymentNotFoundException(Integer id){
        super(String.format("Payment with Id %d not found", id));
    }

}
