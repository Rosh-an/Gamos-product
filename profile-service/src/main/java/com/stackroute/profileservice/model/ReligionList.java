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
@Document(collection="religionList")
public class ReligionList {
    @Id
    private String religion;

    private List<Long> idList;
}
