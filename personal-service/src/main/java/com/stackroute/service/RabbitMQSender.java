package com.stackroute.service;

import com.stackroute.domain.Neo4jObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class RabbitMQSender {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${recommendation.exchange.name}")
    private String exchange;

    @Value("${recommendation.routing.key}")
    private String routingkey;

    public void send(Neo4jObject token) {
        rabbitTemplate.convertAndSend(exchange, routingkey, token);
        logger.info("msg send" );
    }
}
