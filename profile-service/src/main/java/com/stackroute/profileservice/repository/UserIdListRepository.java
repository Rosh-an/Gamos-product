package com.stackroute.profileservice.repository;

import com.stackroute.profileservice.model.UserIdList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserIdListRepository extends MongoRepository<UserIdList, Long> {

    @Query("{'_id': ?0}")
    public List<UserIdList> updateFunc(long id);
}
