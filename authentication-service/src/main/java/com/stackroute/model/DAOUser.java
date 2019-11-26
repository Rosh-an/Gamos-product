package com.stackroute.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="user")
/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data
/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor
@ToString
public class DAOUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String userEmail;
    @Column
    @JsonIgnore
    private String password;
    @Column(columnDefinition = "varchar(255) default 'general'")
    private String loginType;

}
