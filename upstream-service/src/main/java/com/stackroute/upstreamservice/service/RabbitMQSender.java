package com.stackroute.upstreamservice.service;

import com.stackroute.upstreamservice.domain.InfluxSend;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RabbitMQSender {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);

    private AmqpTemplate rabbitTemplate;

    public RabbitMQSender() {
    }

    @Autowired
    public RabbitMQSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${profile.exchange.name}")
    private String exchange;

    @Value("${profile.routing.key}")
    private String routing;

    public void sendMessage(String exchange, String routing, Object data) {
        rabbitTemplate.convertAndSend(exchange, routing, data);
        logger.info("Send Data:{}" , data);
    }

    public void sendProfileMessage(Object data){
        rabbitTemplate.convertAndSend(this.exchange, this.routing, data);
        logger.info("Send Data to Profile :{}" , data);
    }

    public void sendForInflux(String exchange, String routing, InfluxSend msg)
    {
        rabbitTemplate.convertAndSend(exchange, routing, msg);
       logger.info("Send Data :{}" , msg);
    }

}
