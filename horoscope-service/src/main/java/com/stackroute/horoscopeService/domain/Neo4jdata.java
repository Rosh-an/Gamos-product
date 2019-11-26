package com.stackroute.horoscopeService.domain;


import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Neo4jdata {
    private String email;
    private PayLoad payloadDTO;
}
