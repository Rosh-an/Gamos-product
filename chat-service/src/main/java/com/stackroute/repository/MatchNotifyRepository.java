package com.stackroute.repository;

import com.stackroute.domain.Match;
import com.stackroute.domain.MatchNotification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchNotifyRepository extends MongoRepository<Match,String> {
}
