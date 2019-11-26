package com.stackroute.profileservice.model;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;



@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Document(collection="basicPersonalDetails")
public class BasicPersonalDetails {
   private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String nativeCity;
    private String tongue;
    private String phone;
    private String email;
    private String religion;
    private String age;

}
