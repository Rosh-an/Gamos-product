package com.stackroute.service;

import com.stackroute.domain.Chat;
import com.stackroute.exception.ChatAlreadyExistsException;
import com.stackroute.repository.ChatRepository;
import com.stackroute.exception.ChatNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**annotates that the class represents a Service */
@Service
public class ChatServiceImpl implements ChatService {
    private ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    /**for saving the chat */
    @Override
    public Chat saveChat(Chat chat) throws ChatAlreadyExistsException {
        return chatRepository.save(chat);
    }
/**for getting the chat */
    @Override
    public List<Chat> getChat(String fromId) throws ChatNotExistsException {
        return chatRepository.findAll();
    }

}


