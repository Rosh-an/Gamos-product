package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicPersonalDetails {
    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String nativeCity;
    private String tongue;
    private String phone;
    private String age;
    private String religion;
}
