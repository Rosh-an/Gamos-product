package com.stackroute.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="chat")
public class Chat {
    @Id
    private String id;
    private String profileName;
    private List<ChatHistory> chatHistory;

    public Chat(String profileName, String id, List<ChatHistory> chatHistory) {
        this.profileName = profileName;
        this.id = id;
        this.chatHistory = chatHistory;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String id() {
        return id;
    }

    public List<ChatHistory> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<ChatHistory> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public Chat() {
    }

}
