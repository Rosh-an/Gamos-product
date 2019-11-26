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
@Document(collection="userIdList")
public class UserIdList {
    @Id
    private long id=1;
    private List<Long> groomidsList;
    private List<Long> brideidsList;
}
