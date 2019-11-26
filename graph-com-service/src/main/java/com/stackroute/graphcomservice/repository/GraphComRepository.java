package com.stackroute.graphcomservice.repository;

import com.stackroute.graphcomservice.model.*;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface GraphComRepository extends Neo4jRepository {

    /**
    *            NODES
    *******************************************************/




    @Query("CREATE (p:Person{per}) RETURN p")
    Person createPerson(@Param("per") Person person,@Param("gender") String gender);

    @Query("MATCH (n:Person{email:{per}.email}) SET n:MALE RETURN n")
    Person setLabel(@Param("per") Person person,@Param("gender") String gender);

    @Query("MATCH (n:Person{email:{per}.email}) SET n:FEMALE RETURN n")
    Person setLabel1(@Param("per") Person person,@Param("gender") String gender);

    @Query("MERGE (rel:Religion{religion:{rel}.religion}) RETURN rel")
    Religion createReligion(@Param("rel")Religion religion);

    @Query("MERGE (mg:Manglik{manglik:{man}.manglik}) RETURN mg")
    Manglik createManglik(@Param("man") Manglik manglik);

    @Query("MERGE (lan:Language{language:{lan}.language}) RETURN lan")
    Language createLanguage(@Param("lan") Language language);

    @Query("MERGE (cc:CurrentCity{currentCity:{cc}.currentCity}) RETURN cc")
    CurrentCity createCurrentCity(@Param("cc") CurrentCity currentCity);

    @Query("MERGE (nc:NativeCity{nativeCity:{nc}.nativeCity}) RETURN nc")
    NativeCity createNativeCity(@Param("nc") NativeCity nativeCity);

    @Query("MERGE (hb:Hobbies{hobbies:{hb}.hobbies}) RETURN hb")
    Hobbies createHobbies(@Param("hb") Hobbies hobbies);

    @Query("MERGE (int:Interests{interests:{int}.interests}) RETURN int")
    Interests createInterests(@Param("int") Interests interests);

    @Query("MERGE (age:AgeRange{ageRange:{age}.ageRange}) RETURN age")
    AgeRange createAgeRange(@Param("age") AgeRange ageRange);

    @Query("MERGE (hr:HeightRange{heightRange:{hr}.heightRange}) RETURN hr")
    HeightRange createHeightRange(@Param("hr") HeightRange heightRange);

    @Query("MERGE (d:Drink{drink:{d}.drink}) RETURN d")
    Drink createDrink(@Param("d") Drink drink);

    @Query("MERGE (s:Smoke{smoke:{s}.smoke}) RETURN s")
    Smoke createSmoke(@Param("s") Smoke smoke);

    @Query("MERGE (ch:Challenged{challenged:{ch}.challenged}) RETURN ch")
    Challenged createChallenged(@Param("ch") Challenged challenged);

    @Query("MERGE (dt:Diet{diet:{dt}.diet}) RETURN dt")
    Diet createDiet(@Param("dt") Diet diet);

    @Query("MERGE (mar:Marital{maritalStatus:{mar}.maritalStatus}) RETURN mar")
    Marital createMarital(@Param("mar")Marital marital);

    @Query("MERGE (prof:Profession{profession:{prof}.profession}) RETURN prof")
    Profession createProfession(@Param("prof")Profession profession);

    @Query("MERGE (edu:Education{highestEducation:{edu}.highestEducation}) RETURN edu")
    Education createEducation(@Param("edu")Education education);

    @Query("MERGE (com:Company{companyName:{com}.companyName}) RETURN com")
    Company createCompany(@Param("com")Company company);

    @Query("MERGE (exp:Experience{workExperience:{exp}.workExperience}) RETURN exp")
    Experience createExperience(@Param("exp")Experience experience);

    @Query("MERGE (inc:IncomeRange{incomeRange:{inc}.incomeRange}) RETURN inc")
    IncomeRange createIncomeRange(@Param("inc")IncomeRange incomeRange);

    @Query("MERGE (field:Field{courseName:{field}.courseName}) RETURN field")
    Field createField(@Param("field")Field field);

    @Query("MERGE (inst:Institution{institutionName:{inst}.institutionName}) RETURN inst")
    Institution createInstitution(@Param("inst")Institution institution);



    /*******************************************************
    //                    RELATIONSHIPS
    //*******************************************************/

    @Query("MATCH (p:Person{email:{per}.email}),(rel:Religion{religion:{rel}.religion}) CREATE (p)-[r1:OF_RELIGION]->(rel) RETURN p.fname")
    Collection<String> PersonReligion(@Param("per")Person person, @Param("rel")Religion religion);

    @Query("MATCH (p:Person{email:{email}}),(mg:Manglik{manglik:{man}.manglik})\n" +
            "CREATE (p)-[r:MANGLIK_STATUS]->(mg)\n" +
            "RETURN p.fname")
    Collection<String> PersonManglik(@Param("email")String email, @Param("man")Manglik manglik);

    @Query("MATCH (p:Person{email:{per}.email}),(lan:Language{language:{lan}.language})\n" +
            "CREATE (p)-[r:SPEAKS]->(lan)\n" +
            "RETURN p.fname")
    Collection<String> PersonLanguage(@Param("per")Person person, @Param("lan")Language language);

    @Query("MATCH (p:Person{email:{per}.email}),(cc:CurrentCity{currentCity:{cc}.currentCity})\n" +
            "CREATE (p)-[r:LIVES_IN]->(cc)\n" +
            "RETURN p.fname")
    Collection<String> PersonCCity(@Param("per")Person person, @Param("cc")CurrentCity currentCity);

    @Query("MATCH (p:Person{email:{per}.email}),(nc:NativeCity{nativeCity:{nc}.nativeCity})\n" +
            "CREATE (p)-[r:BELONGS_TO]->(nc)\n" +
            "RETURN p.fname")
    Collection<String> PersonNCity(@Param("per")Person person, @Param("nc")NativeCity nativeCity);

    @Query("MATCH (p:Person{email:{per}.email}),(hb:Hobbies{hobbies:{hb}.hobbies})\n" +
            "CREATE (p)-[r:HOBBIES_ARE]->(hb)\n" +
            "RETURN p.fname")
    Collection<String> PersonHobbies(@Param("per")Person person, @Param("hb")Hobbies hobbies);

    @Query("MATCH (p:Person{email:{per}.email}),(int:Interests{interests:{int}.interests})\n" +
            "CREATE (p)-[r:INTERESTS_ARE]->(int)\n" +
            "RETURN p.fname")
    Collection<String> PersonInterests(@Param("per")Person person, @Param("int")Interests interests);

    @Query("MATCH (p:Person{email:{per}.email}),(age:AgeRange{ageRange:{age}.ageRange})\n" +
            "CREATE (p)-[r:OF_AGE]->(age)\n" +
            "RETURN p.fname")
    Collection<String> PersonAgeRange(@Param("per")Person person, @Param("age")AgeRange ageRange);

    @Query("MATCH (p:Person{email:{per}.email}),(hr:HeightRange{heightRange:{hr}.heightRange})\n" +
            "CREATE (p)-[r:OF_HEIGHT{height:{ht}}]->(hr)\n" +
            "RETURN p.fname")
    Collection<String> PersonHeightRange(@Param("per")Person person, @Param("hr")HeightRange heightRange, @Param("ht") float height);

    @Query("MATCH (p:Person{email:{per}.email}),(d:Drink{drink:{d}.drink})\n" +
            "CREATE (p)-[r:DO_DRINK]->(d)\n" +
            "RETURN p.fname")
    Collection<String> PersonDrink(@Param("per")Person person, @Param("d")Drink drink);

    @Query("MATCH (p:Person{email:{per}.email}),(s:Smoke{smoke:{s}.smoke})\n" +
            "CREATE (p)-[r:DO_SMOKE]->(s)\n" +
            "RETURN p.fname")
    Collection<String> PersonSmoke(@Param("per")Person person, @Param("s")Smoke smoke);

    @Query("MATCH (p:Person{email:{per}.email}),(ch:Challenged{challenged:{ch}.challenged})\n" +
            "CREATE (p)-[r:IS_CHALLENGED]->(ch)\n" +
            "RETURN p.fname")
    Collection<String> PersonChallenged(@Param("per")Person person, @Param("ch")Challenged challenged);

    @Query("MATCH (p:Person{email:{per}.email}),(dt:Diet{diet:{dt}.diet})\n" +
            "CREATE (p)-[r:FOLLOWS_DIET]->(dt)\n" +
            "RETURN p.fname")
    Collection<String> PersonDiet(@Param("per")Person person, @Param("dt")Diet diet);

    @Query("MATCH (p:Person{email:{per}.email}),(mar:Marital{maritalStatus:{mar}.maritalStatus})\n" +
            "CREATE (p)-[r:MARITAL_STATUS]->(mar)\n" +
            "RETURN p.fname")
    Collection<String> PersonMarital(@Param("per")Person person, @Param("mar")Marital marital);

    @Query("MATCH (p:Person{email:{email}}),(prof:Profession{profession:{prof}.profession})\n" +
            "CREATE (p)-[r:CURRENT_PROFESSION]->(prof)\n" +
            "RETURN p.fname")
    Collection<String> PersonProfession(@Param("email")String email, @Param("prof")Profession profession);

    @Query("MATCH (p:Person{email:{email}}),(edu:Education{highestEducation:{edu}.highestEducation})\n" +
            "CREATE (p)-[r:HIGHEST_EDUCATION]->(edu)\n" +
            "RETURN p.fname")
    Collection<String> PersonEducation(@Param("email")String email, @Param("edu")Education education);

    @Query("MATCH (prof:Profession{profession:{prof}.profession}),(com:Company{companyName:{com}.companyName})\n" +
            "CREATE (prof)-[r:WORKED_AT]->(com)\n" +
            "RETURN prof.profession")
    Collection<String> PersonCompany(@Param("prof")Profession profession, @Param("com")Company company);

    @Query("MATCH (prof:Profession{profession:{prof}.profession}),(exp:Experience{workExperience:{exp}.workExperience})\n" +
            "CREATE (prof)-[r:WORKED_FOR]->(exp)\n" +
            "RETURN prof.profession")
    Collection<String> PersonExperience(@Param("prof")Profession profession, @Param("exp")Experience experience);

    @Query("MATCH (prof:Profession{profession:{prof}.profession}),(inc:IncomeRange{incomeRange:{inc}.incomeRange})\n" +
            "CREATE (prof)-[r:ANNUAL_INCOME{income:{sal}}]->(inc)\n" +
            "RETURN prof.profession")
    Collection<String> PersonIncomeRange(@Param("prof")Profession profession, @Param("inc")IncomeRange incomeRange,@Param("sal") float income);

    @Query("MATCH (edu:Education{highestEducation:{edu}.highestEducation}),(field:Field{courseName:{field}.courseName})\n" +
            "CREATE (edu)-[r:IN_FIELD]->(field)\n" +
            "RETURN edu.highestEducation")
    Collection<String> PersonField(@Param("edu")Education education, @Param("field")Field field);

    @Query("MATCH (edu:Education{highestEducation:{edu}.highestEducation}),(inst:Institution{institutionName:{inst}.institutionName})\n" +
            "CREATE (edu)-[r:IN_INSTITUTION]->(inst)\n" +
            "RETURN edu.highestEducation")
    Collection<String> PersonInstitution(@Param("edu")Education education, @Param("inst")Institution institution);




}
