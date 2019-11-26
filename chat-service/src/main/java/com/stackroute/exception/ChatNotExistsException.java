package com.stackroute.exception;

public class ChatNotExistsException extends Exception {
    String message;

    public ChatNotExistsException() {
    }

    public ChatNotExistsException(String message) {
        this.message = message;
    }
}
