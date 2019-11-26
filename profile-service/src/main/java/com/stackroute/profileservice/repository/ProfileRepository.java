package com.stackroute.profileservice.repository;

import com.stackroute.profileservice.model.ProfileDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends MongoRepository<ProfileDetails,Long> {
    @Query(value="{'basicPersonalDetails.firstName': ?0}")
    List<ProfileDetails> findByFirstName(final String fName);

    @Query(value="{'basicPersonalDetails.email':?0}")
    List<ProfileDetails> findByEmail(String email);

    @Query(value="{'_id':?0}")
    ProfileDetails findByIdCustom(long id);

/*    @Query(value="{'basicPersonalDetails.age': ?0 ,'basicPersonalDetails.gender':?1 }")
    List<ProfileDetails> findListWithOnlyOneAge(String age,String gender);


    @Query(value="{'basicPersonalDetails.age' :{$gte :?0,$lte :?1},'basicPersonalDetails.gender':?2}")
    List<ProfileDetails> findListWithAgeRange(String age1,String age2, String gender);

    @Query(value="{'basicPersonalDetails.city':?0 ,'basicPersonalDetails.gender' : ?1 }")
    List<ProfileDetails> findListWithOnlyCity(String city,String gender);

    @Query(value="{'basicPersonalDetails.city' : ?0 ,'basicPersonalDetails.age': ?1 ,'basicPersonalDetails.gender' : ?2}")
    List<ProfileDetails> findListWithOneAgeAndCity(String city,String age,String gender);

    @Query(value="{'basicPersonalDetails.city' : ?0 , 'basicPersonalDetails.age' : {$gte : ?1,$lte : ?2}, 'basicPersonalDetails.gender' : ?3 }")
    List<ProfileDetails> findListWithAgeRangeAndCity(String city,String age1, String age2, String gender);*/
}