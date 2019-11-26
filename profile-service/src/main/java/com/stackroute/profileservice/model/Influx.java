package com.stackroute.profileservice.model;


import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;


import java.time.Instant;

@Data
@Measurement(name = "influx")
public class Influx {
    @Column(name = "time")
    private Instant time;
    @Column(name="clickedEmail")
    private String interestedPerson;
    @Column(name="action")
    private String action;
}