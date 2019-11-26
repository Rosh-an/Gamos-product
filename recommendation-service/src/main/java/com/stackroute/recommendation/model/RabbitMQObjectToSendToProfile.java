package com.stackroute.recommendation.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
public class RabbitMQObjectToSendToProfile {
    private String email;
    private List<String> reco_email_list;

}
