package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data
/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    private String userEmail;
    private String password;
}
