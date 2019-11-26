package com.stackroute.upstreamservice.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InfluxData {
    private String userEmail;
    private String clickedEmail;
    private String action;
}
