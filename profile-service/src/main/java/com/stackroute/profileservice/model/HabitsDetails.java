package com.stackroute.profileservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Document(collection="habitsDetails")
public class HabitsDetails {
    private double height;
    private double weight;
    private String hobbies;
    private String interests;
    private String diet;
    private String drink;
    private String smoke;
    private String marital;
    private String challenged;
    private String abroad;
    private String yourself;
}
