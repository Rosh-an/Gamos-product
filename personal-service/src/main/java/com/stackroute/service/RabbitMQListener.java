package com.stackroute.service;

import com.stackroute.domain.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class RabbitMQListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);
    @Autowired
    RabbitMQSender sender;

    /**this  annotation listens the messages received from professional service*/
    @RabbitListener(queues = "${personal.queue.name}")
    public void receiveMsg(PersonalReceived personalReceived){
        try {
           logger.info("Received Msg from Upstream");
            Neo4jObject obj = new Neo4jObject();
            Personal personal = personalReceived.getPersonal();
            BasicPersonalDetails basicPersonalDetails = personal.getBasicPersonalDetails();
            HabitsDetails habitsDetails = personal.getHabitsDetails();
            FamilyDetails familyDetails = personal.getFamilyDetails();


            PersonalObject personalObject = new PersonalObject();
            personalObject.setFirstname(basicPersonalDetails.getFirstName());
            personalObject.setLastname(basicPersonalDetails.getLastName());
            personalObject.setGender(basicPersonalDetails.getGender());
            personalObject.setCurrentcity(basicPersonalDetails.getCity());
            personalObject.setNativecity(basicPersonalDetails.getNativeCity());
            personalObject.setMotherTongue(basicPersonalDetails.getTongue());
            personalObject.setMobileNumber(basicPersonalDetails.getPhone());
            personalObject.setReligion(basicPersonalDetails.getReligion());
            personalObject.setCaste("caste");
            personalObject.setLanguage(basicPersonalDetails.getTongue());
            personalObject.setHeight(habitsDetails.getHeight());
            personalObject.setWeight(habitsDetails.getWeight());
            personalObject.setHobbies(habitsDetails.getHobbies());
            personalObject.setInterests(habitsDetails.getInterests());
            personalObject.setDiet(habitsDetails.getDiet());
            personalObject.setDrink(habitsDetails.getDrink());
            personalObject.setSmoke(habitsDetails.getSmoke());
            personalObject.setMarital(habitsDetails.getMarital());
            personalObject.setChallenged(habitsDetails.getChallenged());
            personalObject.setAbroad(habitsDetails.getAbroad());
            personalObject.setYourself(habitsDetails.getYourself());
            personalObject.setMembers(familyDetails.getMembers());
            personalObject.setFathername(familyDetails.getFatherName());
            personalObject.setFatheroccupation(familyDetails.getFatherOccupation());
            personalObject.setMothername(familyDetails.getMotherName());
            personalObject.setMotheroccupation(familyDetails.getMotherOccupation());
            personalObject.setYoungbrother(familyDetails.getYoungb());
            personalObject.setYoungsister(familyDetails.getYoungs());
            personalObject.setElderbrother(familyDetails.getElderb());
            personalObject.setEldersister(familyDetails.getElders());
            personalObject.setAboutfamily(familyDetails.getAboutf());

            Payload payload = new Payload();
            payload.setPersonalDTO(personalObject);
            payload.setHasPersonal(true);

            obj.setPayloadDTO(payload);
            obj.setEmail(personalReceived.getEmail());
            sender.send(obj);
        }catch (Exception e){
           logger.info(e.getMessage());
        }
    }
}
