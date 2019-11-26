package com.stackroute.profileservice.service;

import com.stackroute.profileservice.model.RabbitMQObjectToRecommendation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${recommendation.exchange.name}")
    private String exchange;

    @Value("${recommendation.routing.key}")
    private String routing;

    public void send(RabbitMQObjectToRecommendation rabbitMQObjectToRecommendation) {
        rabbitTemplate.convertAndSend(exchange, routing,rabbitMQObjectToRecommendation);
    }
}
