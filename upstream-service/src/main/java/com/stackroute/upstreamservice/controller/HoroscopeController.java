package com.stackroute.upstreamservice.controller;


import com.stackroute.upstreamservice.exception.GlobalExceptionHandler;
import com.stackroute.upstreamservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**controller for horoscope service */
@RestController
@CrossOrigin("*")
public class HoroscopeController {
    private static final Logger logger = LoggerFactory.getLogger(HoroscopeController.class);
    private RabbitMQSender rabbitMQSender;
    @Autowired
    public HoroscopeController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Value("${horoscope.exchange.name}")
    private String exchange;

    @Value("${horoscope.routing.key}")
    private String routing;
/**sending horoscope details */
    @PostMapping("/horoscope")
    public ResponseEntity<Object> sendHoroscopeDetails(@RequestBody Object data) throws GlobalExceptionHandler {
       logger.info("Data:{}",data);
        rabbitMQSender.sendMessage(exchange, routing, data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
