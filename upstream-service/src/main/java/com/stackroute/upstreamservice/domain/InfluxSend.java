package com.stackroute.upstreamservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluxSend {
    private String name;
    private String userEmail;
    private String clickedEmail;
    private String action;
}
