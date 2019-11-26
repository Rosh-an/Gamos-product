package com.stackroute.graphcomservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalDTO {

    private String profession;
    private float income;
    private String currentcompany;
    private float experience;
    private String linkedin;
    private String location;

}
