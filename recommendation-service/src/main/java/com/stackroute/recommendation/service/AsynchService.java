package com.stackroute.recommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsynchService {

    PersonService personService;

    @Autowired
    public AsynchService(PersonService personService) {
        this.personService = personService;
    }


    @Async("asyncExecutor")
    public CompletableFuture<List<String>> getRecommendations(String email, String gender)
    {
        return CompletableFuture.completedFuture(personService.getRecommendation(email,gender));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<String>> getRecommendationsPref1(String email,String gender)
    {
        return CompletableFuture.completedFuture(personService.getRecommendationPref1(email,gender));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<String>> getRecommendationsPref2(String email,String gender)
    {
        return CompletableFuture.completedFuture(personService.getRecommendationPref2(email,gender));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<String>> getRecommendationsPref3(String email,String gender)
    {
        return CompletableFuture.completedFuture(personService.getRecommendationPref3(email,gender));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<String>> getRecommendationsPref4(String email,String gender)
    {
        return CompletableFuture.completedFuture(personService.getRecommendationPref4(email,gender));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<String>> getRecommendationsPref5(String email,String gender)
    {
        return CompletableFuture.completedFuture(personService.getRecommendationPref5(email,gender));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<String>> getRecommendationsPref6(String email,String gender)
    {
        return CompletableFuture.completedFuture(personService.getRecommendationPref6(email,gender));
    }

}
