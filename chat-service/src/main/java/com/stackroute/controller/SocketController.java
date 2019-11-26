package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.*;
import com.stackroute.exception.ChatAlreadyExistsException;
import com.stackroute.repository.ChatRepository;
import com.stackroute.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**below class works as controller for socket */
@RestController
@RequestMapping(value = "/api/chat")
@CrossOrigin("*")
public class SocketController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private ChatService chatService;
    private ChatRepository chatRepository;
    private boolean f=true;

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Autowired
    public SocketController(SimpMessagingTemplate simpMessagingTemplate, ChatService chatService, ChatRepository chatRepository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;
        this.chatRepository=chatRepository;

    }

    static final String FI="fromId";

    @MessageMapping("/send/message")
    public ResponseEntity<String> broadcastMessage(String message){
        ResponseEntity responseEntity;
        boolean isSentMsgStored=false;
        boolean isReceivedMsgStored=false;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
            messageConverted = mapper.readValue(message, Map.class);
           logger.info("MessageConverted: {}",messageConverted);
        } catch (IOException e) {
            messageConverted = null;
        }
        if(messageConverted!=null){
            if(messageConverted.containsKey("toId") && messageConverted.get("toId")!=null && !messageConverted.get("toId").equals("")){
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("toId"),messageConverted);

            }else{
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",messageConverted);
            }
        }
        if(chatRepository.existsById(messageConverted.get("toId"))  && chatRepository.findById(messageConverted.get("toId")).isPresent()) {
            for(ChatHistory i:chatRepository.findById(messageConverted.get("toId")).get().getChatHistory())
            {
                if(i.getId().equals(messageConverted.get(FI)))
                    f=false;
            }
        }

        /**
        * Storing Sent message
        */
        Message messageSent = new Message(messageConverted.get("message"),messageConverted.get("date"),this.f,messageConverted.get(FI),messageConverted.get("toId"),"sent");
        if((chatRepository.existsById(messageConverted.get(FI)))&&(!isSentMsgStored))
        {
            Optional<Chat> chat1 = chatRepository.findById(messageConverted.get(FI));
            Chat chat;
            if(chat1.isPresent()) {
                chat = chat1.get();
                List<ChatHistory> chatHistoryList1 = chat.getChatHistory();
                for (ChatHistory i : chatHistoryList1) {
                    if ((i.getId().equals(messageConverted.get("toId"))) && (!isSentMsgStored)) {
                        List<Message> messageList1 = i.getMessage();
                        messageList1.add(messageSent);
                        try {
                            chatService.saveChat(chat);
                        } catch (ChatAlreadyExistsException e) {
                            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
                        }
                        isSentMsgStored = true;
                    }
                }
                if (!isSentMsgStored) {
                    List<Message> messageList = new ArrayList<>();
                    messageList.add(messageSent);
                    ChatHistory chatHistory1 = new ChatHistory(messageConverted.get("toId"), messageConverted.get("toId"), messageList);
                    chatHistoryList1.add(chatHistory1);
                    try {
                        chatService.saveChat(chat);
                    } catch (ChatAlreadyExistsException e) {
                        return  new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
                    }
                    isSentMsgStored = true;
                }
            }
        }
        else {
            List <Message> messageList2 = new ArrayList<>();
            messageList2.add(messageSent);
            ChatHistory chatHistory1=new ChatHistory(messageConverted.get("toId"),messageConverted.get("toId"),messageList2);
            List<ChatHistory> chatHistoryList2=new ArrayList<>();
            chatHistoryList2.add(chatHistory1);
            Chat chat=new Chat(messageConverted.get(FI),messageConverted.get(FI),chatHistoryList2);
            try {
                chatService.saveChat(chat);
            } catch (ChatAlreadyExistsException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
            isSentMsgStored=true;
        }



        /**
        *     Storing Received Message
        */
        Message messageReceived = new Message(messageConverted.get("message"),messageConverted.get("date"),this.f,messageConverted.get(FI),messageConverted.get("toId"),"received");
        if((chatRepository.existsById(messageConverted.get("toId")))&&(!isReceivedMsgStored)) {
            Optional<Chat> chat1 = chatRepository.findById(messageConverted.get("toId"));
            Chat chat;
            if (chat1.isPresent()) {
                chat = chat1.get();
                List<ChatHistory> chatHistoryList1 = chat.getChatHistory();
                for (ChatHistory i : chatHistoryList1) {
                    if ((i.getId().equals(messageConverted.get(FI))) && (!isReceivedMsgStored)) {
                        List<Message> messageList1 = i.getMessage();
                        messageList1.add(messageReceived);
                        try {
                            chatService.saveChat(chat);
                        } catch (ChatAlreadyExistsException e) {
                            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
                        }
                        isReceivedMsgStored = true;
                    }
                }
                if (!isReceivedMsgStored) {
                    List<Message> messageList = new ArrayList<>();
                    messageList.add(messageReceived);
                    ChatHistory chatHistory1 = new ChatHistory(messageConverted.get(FI), messageConverted.get(FI), messageList);
                    chatHistoryList1.add(chatHistory1);
                    try {
                        chatService.saveChat(chat);
                    } catch (ChatAlreadyExistsException e) {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
                    }
                    isReceivedMsgStored = true;
                }
            }
            } else {
                List<Message> messageList2 = new ArrayList<>();
                messageList2.add(messageReceived);
                ChatHistory chatHistory1 = new ChatHistory(messageConverted.get(FI), messageConverted.get(FI), messageList2);
                List<ChatHistory> chatHistoryList2 = new ArrayList<>();
                chatHistoryList2.add(chatHistory1);
                Chat chat = new Chat(messageConverted.get("toId"), messageConverted.get("toId"), chatHistoryList2);
                try {
                    chatService.saveChat(chat);
                } catch (ChatAlreadyExistsException e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
                }
                isReceivedMsgStored = true;
            }


        if((isReceivedMsgStored)&&(isSentMsgStored))
        {
            responseEntity= new ResponseEntity<String>("Successfully Stored",HttpStatus.CREATED);
        }
        else responseEntity = new ResponseEntity<String>("Error", HttpStatus.CONFLICT);
        return responseEntity;
    }

/**for getting chat history
 * @return*/
    @GetMapping("/chatHistory/{id}")
    public ResponseEntity sendChatHistory(@PathVariable("id") String id)
    {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(chatRepository.findById(id), HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


//    @PostMapping("/matchedRecepeint")
//    public ResponseEntity createChatHistory(NewlyMatched newlyMatched)
//    {
//        ResponseEntity responseEntity;
//
//        return responseEntity;
//    }
}
