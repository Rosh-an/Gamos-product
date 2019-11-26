package com.stackroute.profileservice.repository;

import com.stackroute.profileservice.model.AgeList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AgeRepository extends MongoRepository<AgeList, String> {

    @Query("{'_id':?0}")
    public List<AgeList> getAgeList(String age);

}
