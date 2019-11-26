package com.stackroute.service;

import com.stackroute.domain.Chat;
import com.stackroute.exception.ChatAlreadyExistsException;
import com.stackroute.exception.ChatNotExistsException;

import java.util.List;

public interface ChatService {
    public Chat saveChat(Chat chat) throws ChatAlreadyExistsException;
    public List<Chat> getChat(String fromId) throws ChatNotExistsException;
}
