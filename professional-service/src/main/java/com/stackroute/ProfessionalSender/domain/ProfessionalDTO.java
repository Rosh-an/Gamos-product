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
public class ProfessionalDTO {
    private String linkedin;
    private long income;
    private String currentcompany;
    private String profession;
    private String location;
    private String experience;

    public ProfessionalDTO(Professional professional) {
        linkedin = professional.getLinkedin();
        income = professional.getIncome();
        currentcompany = professional.getCurrentCompany();
        profession = professional.getProfession();
        location = professional.getLocation();
        experience = "3";
    }
}
