package com.example.please.error;

public class ClientErrorResponse {
    private int status;
    private String message;


    public ClientErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;

    }

    public ClientErrorResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
