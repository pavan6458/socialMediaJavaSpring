package com.example.InstaPost.exception;

public class DataNotErrorResponse {
    private String statusCode;
    private String message;


    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
