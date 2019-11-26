package com.stackroute.profileservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Document(collection="horoscopeDetails")
public class HoroscopeDetails {
    private String timeOfBirth;
    private String placeOfBirth;
    private String dateOfBirth;
    private String manglik;
}
