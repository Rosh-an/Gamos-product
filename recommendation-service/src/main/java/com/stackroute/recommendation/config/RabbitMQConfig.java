package com.stackroute.recommendation.config;

import com.stackroute.recommendation.service.RabbitMQListener;
import com.stackroute.recommendation.service.RabbitMQSender;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${recommendation.queue.name}")
    String queueName;


    @Value("${recommendation.exchange.name}")
    String exchange;

    @Value("${recommendation.routing.key}")
    String routingkey;



    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange() {
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
    public RabbitMQSender sender() {
        return new RabbitMQSender();
    }

    @Bean
    public RabbitMQListener rabbitListener(){
        return new RabbitMQListener();
    }

}

