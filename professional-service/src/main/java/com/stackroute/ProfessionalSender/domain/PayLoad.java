package com.stackroute.ProfessionalSender.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor
public class PayLoad {
    private EducationDTO educationDTO;
    private ProfessionalDTO professionalDTO;
    private boolean hasProfessional;
    private boolean hasEducation;
}
