package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personal {
    private BasicPersonalDetails basicPersonalDetails;
    private HabitsDetails habitsDetails;
    private FamilyDetails familyDetails;
}
