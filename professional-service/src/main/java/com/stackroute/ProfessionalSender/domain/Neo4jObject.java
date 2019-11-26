package com.stackroute.ProfessionalSender.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data
/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor
public class Neo4jObject {

    private String email;
    private PayLoad payloadDTO;
}
