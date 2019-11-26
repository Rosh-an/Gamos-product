package com.stackroute.graphcomservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Person {

    @Id
    private String email;
    private String fname;
    private String lname;
//    private String name=this.fname+this.lname;
    private String name;
    private String number;
    private String gender;
    private String DOB;
    private String motherTongue;
    private float weight;
    private String aboutYourself;
    private String fatherName;
    private String fatherOccupation;
    private String motherName;
    private String motherOccupation;
    private String elderBrother;
    private String youngerBrother;
    private String elderSister;
    private String youngerSister;
    private String aboutFamily;

    @Relationship(type="OF_RELIGION",direction = Relationship.OUTGOING)
    private Religion religion;

    @Relationship(type="SPEAKS",direction = Relationship.OUTGOING)
    private List<Language> language;

    @Relationship(type="MANGLIK_STATUS",direction = Relationship.OUTGOING)
    private Manglik manglik;

    @Relationship(type="LIVES_IN",direction = Relationship.OUTGOING)
    private CurrentCity currentCity;

    @Relationship(type="BELONGS_TO",direction = Relationship.OUTGOING)
    private NativeCity nativeCity;

    @Relationship(type="FOLLOWS_DIET",direction = Relationship.OUTGOING)
    private Diet diet;

    @Relationship(type="IS_CHALLENGED",direction = Relationship.OUTGOING)
    private Challenged challenged;

    @Relationship(type="DO_SMOKE",direction = Relationship.OUTGOING)
    private Smoke smoke;

    @Relationship(type="DO_DRINK",direction = Relationship.OUTGOING)
    private Drink drink;

    @Relationship(type="HIGHEST_EDUCATION",direction = Relationship.INCOMING)
    private Education education;

    @Relationship(type="HOBBIES_ARE",direction = Relationship.OUTGOING)
    private List<Hobbies> hobbies;

    @Relationship(type="INTERESTS_ARE",direction = Relationship.OUTGOING)
    private List<Interests> interests;

    @Relationship(type="OF_HEIGHT",direction = Relationship.OUTGOING)
    private HeightRange heightRange;

    @Relationship(type="OF_AGE",direction = Relationship.OUTGOING)
    private AgeRange ageRange;

    @Relationship(type="MARITAL_STATUS",direction = Relationship.OUTGOING)
    private Marital marital;

    @Relationship(type="CURRENT_PROFESSION",direction = Relationship.INCOMING)
    private Profession profession;

}
