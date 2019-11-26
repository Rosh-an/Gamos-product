package com.stackroute.ProfessionalSender.domain;

import lombok.*;

@Data
/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor
@Getter
@Setter
public class Education {
    private String education_qualification;
    private String college;
    private String city;
    private String passingYear;
}
