package com.stackroute.profileservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Document(collection="cityList")
public class CityList {
    @Id
    private String city;

    private List<Long> idList;
}
