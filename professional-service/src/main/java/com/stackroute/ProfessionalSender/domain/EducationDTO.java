package com.stackroute.ProfessionalSender.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data
/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor
public class EducationDTO {
    private String education;
    private String college;
    private String city;
    private String passingyear;
    private String field;

    public EducationDTO(Education edu) {
        education = edu.getEducation_qualification();
        college = edu.getCollege();
        city = edu.getCity();
        passingyear = edu.getPassingYear();
        field = "field";
    }
}
