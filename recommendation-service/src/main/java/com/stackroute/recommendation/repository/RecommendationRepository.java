package com.stackroute.recommendation.repository;


import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecommendationRepository extends Neo4jRepository {


    /**
    *                      NODES
    *******************************************************/

    @Query("MATCH (n:Person{email:{email}})-[:LIVES_IN]->(r)-[:LIVES_IN]-(friend_rec)-[:OF_AGE]->(s)-[:OF_AGE]\n"
    + "-(n:Person{email:{email}})-[:DO_DRINK]->(t)-[:DO_DRINK]-(friend_rec)-[:DO_SMOKE]->(u)-[:DO_SMOKE]\n"+
    "-(n:Person{email:{email}})-[:SPEAKS]->(v)-[:SPEAKS]-(friend_rec)-[:MANGLIK_STATUS]->(w)-[:MANGLIK_STATUS]\n"+
    "-(n:Person{email:{email}})-[:OF_HEIGHT]->(x)-[:OF_HEIGHT]-(friend_rec)-[:MARITAL_STATUS]->(y)-[:MARITAL_STATUS]\n"+
    "-(n:Person{email:{email}})-[:OF_RELIGION]->(z)-[:OF_RELIGION]-(friend_rec) where friend_rec.gender<>{gender} return friend_rec.email")
    List<String> getRecommendation(@Param("email")String email, @Param("gender")String gender);


    /**Without manglik status relation**/

    @Query("MATCH (n:Person{email:{email}})-[:LIVES_IN]->(r)-[:LIVES_IN]-(friend_rec)-[:OF_AGE]->(s)-[:OF_AGE]\n"
            + "-(n:Person{email:{email}})-[:DO_DRINK]->(t)-[:DO_DRINK]-(friend_rec)-[:DO_SMOKE]->(u)-[:DO_SMOKE]\n"+
            "-(n:Person{email:{email}})-[:SPEAKS]->(v)-[:SPEAKS]\n"+
            "-(friend_rec)-[:OF_HEIGHT]->(x)-[:OF_HEIGHT]-(n:Person{email:{email}})-[:MARITAL_STATUS]->(y)-[:MARITAL_STATUS]\n"+
            "-(friend_rec)-[:OF_RELIGION]->(z)-[:OF_RELIGION]-(n:Person{email:{email}}) where friend_rec.gender<>{gender} return friend_rec.email")
    List<String> getRecommendationPref1(@Param("email")String email, @Param("gender")String gender);


    /**Without livesin relation**/

    @Query("MATCH (n:Person{email:{email}})-[:OF_AGE]->(s)-[:OF_AGE]\n"
            + "-(friend_rec)-[:DO_DRINK]->(t)-[:DO_DRINK]-(n:Person{email:{email}})-[:DO_SMOKE]->(u)-[:DO_SMOKE]\n"+
            "-(friend_rec)-[:SPEAKS]->(v)-[:SPEAKS]\n"+
            "-(n:Person{email:{email}})-[:OF_HEIGHT]->(x)-[:OF_HEIGHT]-(friend_rec)-[:MARITAL_STATUS]->(y)-[:MARITAL_STATUS]\n"+
            "-(n:Person{email:{email}})-[:OF_RELIGION]->(z)-[:OF_RELIGION]-(friend_rec) where friend_rec.gender<>{gender} return friend_rec.email")
    List<String> getRecommendationPref2(@Param("email")String email, @Param("gender")String gender);

    /**Without DoSmoke and DoDrink relation**/

    @Query("MATCH (n:Person{email:{email}})-[:OF_AGE]->(s)-[:OF_AGE]\n"
            +"-(friend_rec)-[:SPEAKS]->(v)-[:SPEAKS]\n"+
            "-(n:Person{email:{email}})-[:OF_HEIGHT]->(x)-[:OF_HEIGHT]-(friend_rec)-[:MARITAL_STATUS]->(y)-[:MARITAL_STATUS]\n"+
            "-(n:Person{email:{email}})-[:OF_RELIGION]->(z)-[:OF_RELIGION]-(friend_rec) where friend_rec.gender<>{gender} return friend_rec.email")
    List<String> getRecommendationPref3(@Param("email")String email, @Param("gender")String gender);


    /**Without Religion Relation**/

    @Query("MATCH (n:Person{email:{email}})-[:OF_AGE]->(s)-[:OF_AGE]\n"
            +"-(friend_rec)-[:SPEAKS]->(v)-[:SPEAKS]\n"+
            "-(n:Person{email:{email}})-[:OF_HEIGHT]->(x)-[:OF_HEIGHT]-(friend_rec)-[:MARITAL_STATUS]->(y)-[:MARITAL_STATUS]\n"+
            "-(n:Person{email:{email}}) where friend_rec.gender<>{gender} return friend_rec.email")
    List<String> getRecommendationPref4(@Param("email")String email, @Param("gender")String gender);


    /**Without Age Relation**/

    @Query("MATCH (n:Person{email:{email}})-[:SPEAKS]->(v)-[:SPEAKS]\n"+
            "-(friend_rec)-[:OF_HEIGHT]->(x)-[:OF_HEIGHT]-(n:Person{email:{email}})-[:MARITAL_STATUS]->(y)-[:MARITAL_STATUS]\n"+
            "-(friend_rec) where friend_rec.gender<>{gender} return friend_rec.email")
    List<String> getRecommendationPref5(@Param("email")String email, @Param("gender")String gender);

    /**Without Height Relation**/

    @Query("MATCH (n:Person{email:{email}})-[:SPEAKS]->(v)-[:SPEAKS]\n"+
            "-(friend_rec)-[:MARITAL_STATUS]->(y)-[:MARITAL_STATUS]\n"+
            "-(n:Person{email:{email}}) where friend_rec.gender<>{gender} return friend_rec.email")
    List<String> getRecommendationPref6(@Param("email")String email, @Param("gender")String gender);






}
