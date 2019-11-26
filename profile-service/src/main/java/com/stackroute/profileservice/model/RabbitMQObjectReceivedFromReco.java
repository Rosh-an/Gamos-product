package com.stackroute.profileservice.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RabbitMQObjectReceivedFromReco {
    private String email;
    private List<String> reco_email_list;
}
