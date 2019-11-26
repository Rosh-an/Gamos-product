package com.stackroute.graphcomservice.service;

import com.stackroute.graphcomservice.model.DTO;
import com.stackroute.graphcomservice.controller.GraphComController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RabbitMQListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener .class);


    @Autowired
    private PersonService personService;
    @Autowired
    GraphComController graphComController;

    @RabbitListener(queues = "${neo4j.queue.name}")
    public void getObj(DTO dto)
    {
        logger.info("following from response entity: {}",dto);
        try {
            logger.info("saveDTO: {}",graphComController.saveDTO(dto));
        }catch (Exception e){
           logger.info(e.getMessage());
        }
    }




}
