package com.stackroute.horoscopeService.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoroscopeDTO {
    private String timeofbirth;
    private String placeofbirth;
    private String manglik;
}
