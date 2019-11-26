package com.stackroute.graphcomservice.model;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class email {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
}
