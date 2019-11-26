package com.stackroute.profileservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Document(collection="educationalDetails")
public class EducationalDetails {
     private String education;
     private String college;
     private String city;
     private String passingYear;
}
