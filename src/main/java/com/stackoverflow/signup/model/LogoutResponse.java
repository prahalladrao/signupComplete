package com.stackoverflow.signup.model;

import org.springframework.stereotype.Component;

@Component
public class LogoutResponse {
    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
