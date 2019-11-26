package com.stackroute.recommendation.service;

import com.stackroute.recommendation.model.RabbitMQObjectToSendToProfile;
import com.stackroute.recommendation.repository.RecommendationRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    private AmqpTemplate rabbitTemplate;



    @Value("${profile.exchange.name}")
    private String exchange;

    @Value("${profile.routing.key}")
    private String routingkey;

    public void send(RabbitMQObjectToSendToProfile rabbitMQObjectToSendToProfile) {
        rabbitTemplate.convertAndSend(exchange, routingkey,rabbitMQObjectToSendToProfile);
    }
}
