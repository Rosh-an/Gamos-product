package com.stackroute.ProfessionalSender.config;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Indicates this as a configuration class
 */
@Configuration
public class RabbitMQConfig {
    @Value("${professional.queue.name}")
    String queueName;

    @Value("${professional.exchange.name}")
    String exchange;

    @Value("${professional.routing.key}")
    private String routingkey;


    /**
     * Creates a bean of queue for the application
     */
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }



    /**
     * Creates a exchange bean for the application
     */
    @Bean
    DirectExchange exchange1() {
        return new DirectExchange(exchange);
    }


    /**
     * Creates a binding bean for the application
     */
    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }


    /**
     * Creates a jsonMessageConverter bean for the application
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    /**
     * Creates a rabbitTemplate bean for the application
     */
    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
