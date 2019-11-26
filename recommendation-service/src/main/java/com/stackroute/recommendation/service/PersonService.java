package com.stackroute.recommendation.service;


import java.util.List;

public interface PersonService {


        List<String> getRecommendation(String email, String gender);
        List<String> getRecommendationPref1(String email, String gender);
        List<String> getRecommendationPref2(String email, String gender);
        List<String> getRecommendationPref3(String email, String gender);
        List<String> getRecommendationPref4(String email, String gender);
        List<String> getRecommendationPref5(String email, String gender);
        List<String> getRecommendationPref6(String email, String gender);



}
