package com.stackroute.exception;

public class ChatAlreadyExistsException extends Exception {

   String message;

    public ChatAlreadyExistsException() {
    }

    public ChatAlreadyExistsException(String message) {
        this.message = message;
    }
}
