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
public class Education {
    @Id
    private String highestEducation;
    private String passingYear;

    @Relationship(type="IN_FIELD",direction = Relationship.OUTGOING)
    private Field field;

    @Relationship(type="IN_INSTITUTION",direction = Relationship.OUTGOING)
    private Institution institution;
}
