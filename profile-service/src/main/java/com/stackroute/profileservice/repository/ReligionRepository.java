package com.stackroute.profileservice.repository;

import com.stackroute.profileservice.model.ReligionList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReligionRepository extends MongoRepository<ReligionList,String> {
    @Query("{'_id':?0}")
    List<ReligionList> getReligionList(String religion);
}
