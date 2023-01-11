package com.example.encryption1;

public class senderModel {
    String from;
    String message;

    public senderModel(String from, String message) {
        this.from = from;
        this.message = message;
    }

    public senderModel() {
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}