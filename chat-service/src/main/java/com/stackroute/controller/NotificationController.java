package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Match;
import com.stackroute.domain.MatchNotification;
import com.stackroute.repository.MatchNotifyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping(value = "/api/notify")
@CrossOrigin("*")
public class NotificationController {
    private SimpMessagingTemplate simpMessagingTemplate;
    private MatchNotifyRepository matchNotifyRepository;
    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Autowired
    public NotificationController(SimpMessagingTemplate simpMessagingTemplate, MatchNotifyRepository matchNotifyRepository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.matchNotifyRepository = matchNotifyRepository;
    }

    @MessageMapping("/send/notification")
    public ResponseEntity<String> broadcastNotification(String msg)
    {
        ResponseEntity responseEntity;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
            messageConverted = mapper.readValue(msg, Map.class);
            logger.info("MessageConverted: {}",messageConverted);
        } catch (IOException e) {
            messageConverted = null;
        }
        if(messageConverted!=null){
            if(messageConverted.containsKey("toId") && messageConverted.get("toId")!=null && !messageConverted.get("toId").equals("")){
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("toId"),messageConverted);
            }
        }

        MatchNotification matchNotification= new MatchNotification(messageConverted.get("fromId"),messageConverted.get("notification"));
        if(matchNotifyRepository.existsById(messageConverted.get("toId"))){
            Optional<Match> match1 = matchNotifyRepository.findById(messageConverted.get("toId"));
            Match match;
            if (match1.isPresent()) {
                match = match1.get();
                List<MatchNotification> matchNotificationList= match.getMatchNotifications();
                matchNotificationList.add(matchNotification);
                try{
                    matchNotifyRepository.save(match);
                } catch (Exception e){
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
                }
            }
        } else{
            List<MatchNotification> matchNotificationList= new ArrayList<>();
            matchNotificationList.add(matchNotification);
            Match match=new Match(messageConverted.get("toId"),messageConverted.get("toId"),matchNotificationList);
            try{
                matchNotifyRepository.save(match);
            } catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }
        responseEntity=new ResponseEntity<String>("Success", HttpStatus.ACCEPTED);
        return responseEntity;
    }


    @GetMapping("/notificationHistory/{id}")
    public ResponseEntity retreiveNotifications(@PathVariable String id)
    {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(matchNotifyRepository.findById(id), HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
