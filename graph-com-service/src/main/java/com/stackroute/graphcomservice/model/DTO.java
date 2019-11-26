package com.stackroute.graphcomservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTO {
    private String email;
    private PayloadDTO payloadDTO;

    @Override
    public String toString() {
        return "DTO{" +
                "email='" + email + '\'' +
                ", payloadDTO=" + payloadDTO +
                '}';
    }
}
