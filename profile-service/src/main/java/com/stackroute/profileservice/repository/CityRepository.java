package com.stackroute.profileservice.repository;

import com.stackroute.profileservice.model.CityList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CityRepository extends MongoRepository<CityList,String> {

    @Query("{'city':?0}")
    List<CityList> getCityList(String city);
}
