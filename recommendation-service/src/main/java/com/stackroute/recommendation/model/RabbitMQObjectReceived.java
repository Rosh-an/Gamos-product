package com.stackroute.recommendation.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RabbitMQObjectReceived {
    private String email;
    private String gender;
}
