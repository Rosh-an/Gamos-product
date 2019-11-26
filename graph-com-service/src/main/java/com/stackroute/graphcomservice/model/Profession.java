package com.stackroute.graphcomservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Profession {
    @Id
    private String profession;
    private String linkedIn;

    @Relationship(type="ANNUAL_INCOME",direction = Relationship.OUTGOING)
    private Income income;

    @Relationship(type="WORKED_FOR",direction = Relationship.OUTGOING)
    private Experience experience;

    @Relationship(type="WORKED_AT",direction = Relationship.OUTGOING)
    private Company company;
}
