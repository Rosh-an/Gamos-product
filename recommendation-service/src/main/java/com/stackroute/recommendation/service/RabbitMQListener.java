package com.stackroute.recommendation.service;

import com.stackroute.recommendation.model.RabbitMQObjectReceived;
import com.stackroute.recommendation.model.RabbitMQObjectToSendToProfile;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class RabbitMQListener {
    @Autowired
    RabbitMQSender rabbitMQSender;
//    @Autowired
//    private PersonService personService;

    @Autowired
    private AsynchService asynchService;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);


    @RabbitListener(queues = "${recommendation.queue.name}")
    public void receiveObjectfromProfile(RabbitMQObjectReceived rabbitMQObjectReceived) throws ExecutionException, InterruptedException {
//       logger.info("Received profile details from profile" , rabbitMQObjectReceived.email," ",rabbitMQObjectReceived.gender);

        CompletableFuture<List<String>> listall = asynchService.getRecommendations(rabbitMQObjectReceived.getEmail(),rabbitMQObjectReceived.getGender());
        CompletableFuture<List<String>> list1 = asynchService.getRecommendationsPref1(rabbitMQObjectReceived.getEmail(),rabbitMQObjectReceived.getGender());
        CompletableFuture<List<String>> list2 = asynchService.getRecommendationsPref2(rabbitMQObjectReceived.getEmail(),rabbitMQObjectReceived.getGender());
        CompletableFuture<List<String>> list3 = asynchService.getRecommendationsPref3(rabbitMQObjectReceived.getEmail(),rabbitMQObjectReceived.getGender());
        CompletableFuture<List<String>> list4 = asynchService.getRecommendationsPref4(rabbitMQObjectReceived.getEmail(),rabbitMQObjectReceived.getGender());
        CompletableFuture<List<String>> list5 = asynchService.getRecommendationsPref5(rabbitMQObjectReceived.getEmail(),rabbitMQObjectReceived.getGender());
        CompletableFuture<List<String>> list6 = asynchService.getRecommendationsPref6(rabbitMQObjectReceived.getEmail(),rabbitMQObjectReceived.getGender());
        CompletableFuture.allOf(list1,list2,list3,list4,list5,list6).join();
        LinkedHashSet<String> tempSet =new LinkedHashSet<>(listall.get());
        tempSet.addAll(list1.get());
        tempSet.addAll(list2.get());
        tempSet.addAll(list3.get());
        tempSet.addAll(list4.get());
        tempSet.addAll(list5.get());
        tempSet.addAll(list6.get());
        List<String> finalList = new ArrayList<>(tempSet);

        RabbitMQObjectToSendToProfile rabbitMQObjectToSendToProfile = new RabbitMQObjectToSendToProfile(rabbitMQObjectReceived.getEmail(),finalList);
        rabbitMQSender.send(rabbitMQObjectToSendToProfile);
    }



}
