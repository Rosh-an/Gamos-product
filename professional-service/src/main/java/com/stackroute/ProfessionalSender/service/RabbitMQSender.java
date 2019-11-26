package com.stackroute.ProfessionalSender.service;

import com.stackroute.ProfessionalSender.domain.Neo4jObject;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/** Indicates this is a service class*/
@Service
public class RabbitMQSender {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${recommendation.exchange.name}")
    private String exchange;

    @Value("${recommendation.routing.key}")
    private String routingkey;

/**  send method for sending data to neo4j*/
    public void send(Neo4jObject company) {
        rabbitTemplate.convertAndSend(exchange, routingkey, company);
        logger.info("Send msg");
    }
}
