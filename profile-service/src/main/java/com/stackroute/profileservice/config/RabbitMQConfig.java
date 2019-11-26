package com.stackroute.profileservice.config;


import com.stackroute.profileservice.service.RabbitMQListener;
import com.stackroute.profileservice.service.RabbitMQSender;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${profile.queue.name}")
    String queueName;

    @Value("${profile.exchange.name}")
    String exchange;

    @Value("${profile.routing.key}")
    private String routingkey;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange1() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitMQListener rabbitListener(){
        return new RabbitMQListener();
    }

    @Bean
    public RabbitMQSender sender() {
        return new RabbitMQSender();
    }
}
