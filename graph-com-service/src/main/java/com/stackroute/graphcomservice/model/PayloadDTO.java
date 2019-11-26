package com.stackroute.graphcomservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayloadDTO {

    private PersonalDTO personalDTO;
    private boolean hasPersonal;
    private EducationDTO educationDTO;
    private boolean hasEducation;
    private ProfessionalDTO professionalDTO;
    private boolean hasProfessional;
    private HoroscopeDTO horoscopeDTO;
    private boolean hasHoroscope;

}
