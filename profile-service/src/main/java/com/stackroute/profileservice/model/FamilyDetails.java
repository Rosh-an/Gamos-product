package com.stackroute.profileservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Document(collection="familyDetails")
public class FamilyDetails {
    private int members;
    private String fatherName;
    private String fatherOccupation;
    private String motherName;
    private String motherOccupation;
    private String youngb;
    private String youngs;
    private String elderb;
    private String elders;
    private String aboutf;
}
