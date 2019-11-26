package com.stackroute.graphcomservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Experience {
    @Id
    private float workExperience;
}
