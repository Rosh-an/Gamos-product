package com.stackroute.profileservice.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LikeProfileEmail {
    private String user_email;
    private String liked_email;

}
