package com.stackroute.upstreamservice.controller;

import com.stackroute.upstreamservice.exception.GlobalExceptionHandler;
import com.stackroute.upstreamservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**controller for professional Service */
@RestController
@CrossOrigin("*")
public class ProfessionalController {


    private RabbitMQSender rabbitMQSender;
    @Autowired
    public ProfessionalController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Value("${professional.exchange.name}")
    private String exchange;

    @Value("${professional.routing.key}")
    private String routing;

    @PostMapping("/professional")
    public ResponseEntity<Object> sendProfessionalDetails(@RequestBody Object data) throws GlobalExceptionHandler {
        rabbitMQSender.sendMessage(exchange, routing, data);
        return  new ResponseEntity<>(data, HttpStatus.OK);
    }
}
