package com.stackroute.recommendation.service;


import com.stackroute.recommendation.repository.RecommendationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceimpl implements PersonService {


    @Autowired
    RecommendationRepository recommendationRepository;




    @Override
    public List<String> getRecommendation(String email, String gender) {
     return recommendationRepository.getRecommendation(email,gender);
    }

    @Override
    public List<String> getRecommendationPref1(String email, String gender) {
        return recommendationRepository.getRecommendationPref1(email,gender);
    }

    @Override
    public List<String> getRecommendationPref2(String email, String gender) {
        return recommendationRepository.getRecommendationPref2(email,gender);

    }

    @Override
    public List<String> getRecommendationPref3(String email, String gender) {
        return recommendationRepository.getRecommendationPref3(email,gender);

    }

    @Override
    public List<String> getRecommendationPref4(String email, String gender) {
        return recommendationRepository.getRecommendationPref4(email,gender);

    }

    @Override
    public List<String> getRecommendationPref5(String email, String gender) {
        return recommendationRepository.getRecommendationPref5(email,gender);

    }

    @Override
    public List<String> getRecommendationPref6(String email, String gender) {
        return recommendationRepository.getRecommendationPref6(email,gender);

    }


}
