package com.stackroute.ProfessionalSender.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalObject {
    private Professional professional;
    private Education education;
}
