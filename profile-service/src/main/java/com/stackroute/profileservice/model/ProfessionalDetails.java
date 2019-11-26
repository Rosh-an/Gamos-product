package com.stackroute.profileservice.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Document(collection="professionalDetails")
public class ProfessionalDetails {
    private String profession;
    private String income;
    private String currentCompany;
    private String experience;
    private String linkedin;
    private String location;
}
