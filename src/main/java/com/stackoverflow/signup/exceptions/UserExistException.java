package com.stackoverflow.signup.exceptions;

public class UserExistException extends RuntimeException
{
    private int statusCode;


    public UserExistException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;

    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


}
