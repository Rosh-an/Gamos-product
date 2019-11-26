package com.stackroute.horoscopeService.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayLoad {
    private HoroscopeDTO horoscopeDTO;
    private boolean hasHoroscope;
}
