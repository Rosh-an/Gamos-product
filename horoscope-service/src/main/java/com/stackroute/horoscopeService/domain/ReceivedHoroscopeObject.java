package com.stackroute.horoscopeService.domain;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedHoroscopeObject {
    private String email;
    private horoscopedata horoscopedata;
}
