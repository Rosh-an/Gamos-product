package com.stackroute.horoscopeService.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class horoscopedata {
    private String dateOfBirth;
    private String placeOfBirth;
    private String timeOfBirth;
    private String manglik;

}
