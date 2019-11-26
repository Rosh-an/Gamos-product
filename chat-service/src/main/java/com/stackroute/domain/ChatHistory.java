package com.stackroute.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public class ChatHistory {
    @Id
    private String id;
    private String receiverName;
    private List<Message> message;

    public ChatHistory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public ChatHistory(String id, String receiverName, List<Message> message) {
        this.id = id;
        this.receiverName = receiverName;
        this.message = message;
    }
}
