package com.stackroute.profileservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Query.query;
@Service
public class GenerateSequence {
    private MongoOperations mongoOperations;

    @Autowired
    public GenerateSequence(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(seqName));

        DatabaseSequence counter = mongoOperations.findAndModify(query,
                new Update().inc("seq",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
