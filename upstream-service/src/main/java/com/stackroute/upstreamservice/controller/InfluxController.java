package com.stackroute.upstreamservice.controller;

import com.stackroute.upstreamservice.domain.InfluxData;
import com.stackroute.upstreamservice.domain.InfluxSend;
import com.stackroute.upstreamservice.exception.GlobalExceptionHandler;
import com.stackroute.upstreamservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class InfluxController {
    private RabbitMQSender rabbitMQSender;
    @Autowired
    public InfluxController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Value("${influx.exchange.name}")
    private String exchange;

    @Value("${influx.routing.key}")
    private String routing;

    @PostMapping("/influx")
    public ResponseEntity<InfluxData> sendInfluxDetails(@RequestBody InfluxData influxData) throws GlobalExceptionHandler {

        InfluxSend influxSend = new InfluxSend();
        influxSend.setName("influx");
        influxSend.setUserEmail(influxData.getUserEmail());
        influxSend.setClickedEmail(influxData.getClickedEmail());
        influxSend.setAction(influxData.getAction());
        rabbitMQSender.sendForInflux(exchange, routing, influxSend);
        return  new ResponseEntity<>(influxData, HttpStatus.OK);
    }
}
