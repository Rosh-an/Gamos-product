package com.stackroute.upstreamservice.controller;

import com.stackroute.upstreamservice.exception.GlobalExceptionHandler;
import com.stackroute.upstreamservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class PersonalController {

    private RabbitMQSender rabbitMQSender;
    @Autowired
    public PersonalController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Value("${personal.exchange.name}")
    private String exchange;

    @Value("${personal.routing.key}")
    private String routing;

    @PostMapping("/personal")
    public ResponseEntity<Object> sendPersonalDetails(@RequestBody Object data) throws GlobalExceptionHandler {
        rabbitMQSender.sendMessage(exchange, routing, data);
        return  new ResponseEntity<>(data, HttpStatus.OK);
    }
}
