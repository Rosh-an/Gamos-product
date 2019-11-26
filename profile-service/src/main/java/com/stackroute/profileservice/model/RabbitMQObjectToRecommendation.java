package com.stackroute.profileservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RabbitMQObjectToRecommendation {
    private String email;
    private String gender;
}
