package com.stackroute.domain;



public class Message {
    String message1;
    String date;
    boolean isInitiater;
    String sender;
    String receiver;
    String msgType;

    public Message(String message, String date, boolean isInitiater, String sender, String receiver, String msgType) {
        this.message1 = message;
        this.date = date;
        this.isInitiater = isInitiater;
        this.sender = sender;
        this.receiver = receiver;
        this.msgType = msgType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public boolean isInitiater() {
        return isInitiater;
    }

    public void setInitiater(boolean initiater) {
        isInitiater = initiater;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Message() {
    }

    public String getMessage() {
        return message1;
    }

    public void setMessage(String message) {
        this.message1 = message;
    }
}
