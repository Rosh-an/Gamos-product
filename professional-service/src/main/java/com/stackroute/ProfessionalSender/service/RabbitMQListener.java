package com.stackroute.ProfessionalSender.service;

import com.stackroute.ProfessionalSender.domain.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Indicates this as a component class
 */
@Component
public class RabbitMQListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @Autowired
    RabbitMQSender sender;
    @RabbitListener(queues = "${professional.queue.name}")
    public void receiveMsg(ReceivedObject receivedObject) {
       logger.info("Received Msg from Upstream");
        Neo4jObject object = new Neo4jObject();
        object.setEmail(receivedObject.getEmail());
        PayLoad payLoad = new PayLoad();
        payLoad.setEducationDTO(new EducationDTO(receivedObject.getProfessionalObject().getEducation()));
        payLoad.setProfessionalDTO(new ProfessionalDTO(receivedObject.getProfessionalObject().getProfessional()));
        payLoad.setHasEducation(true);
        payLoad.setHasProfessional(true);
        object.setPayloadDTO(payLoad);
        sender.send(object);
    }
    }
