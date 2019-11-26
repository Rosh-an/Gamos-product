package com.stackroute.profileservice.controller;


import com.stackroute.profileservice.model.Influx;
import com.stackroute.profileservice.model.ProfileDetails;
import com.stackroute.profileservice.model.UserEmail;
import com.stackroute.profileservice.service.ProfileService;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/viewprofile/")
@CrossOrigin("*")
public class InfluxController {
    private ProfileService profileService;

    @Autowired
    public InfluxController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public void addToRecoList(@RequestBody UserEmail userEmail)
    {
        org.influxdb.InfluxDB influxDB =  InfluxDBFactory.connect("http://15.206.46.12:8086", "root", "root");
        influxDB.enableGzip();
        influxDB.setDatabase("telegraf");
        influxDB.setRetentionPolicy("autogen");
        Pong response = influxDB.ping();
        System.out.println(response.toString());
        if (response.getVersion().equalsIgnoreCase("unknown")) {
            System.out.println("Error pinging server.");
        }
        Query query = new Query("select clickedEmail,action from influx where  userEmail='"+userEmail.email+"' ","telegraf");
        QueryResult queryResult = influxDB.query(query);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper(); // thread-safe - can be reused
        List<Influx> emailList = resultMapper.toPOJO(queryResult, Influx.class);

        for (int i = 1; i <emailList.size(); i=i+2) {
            System.out.println(Duration.between(emailList.get(i - 1).getTime(), emailList.get(i).getTime()).getSeconds());
            System.out.println(emailList.get(i - 1));
            System.out.println(emailList.get(i));
            if (emailList.get(i).getAction().equalsIgnoreCase("exited") && emailList.get(i - 1).getAction().equalsIgnoreCase("entered") && emailList.get(i - 1).getInterestedPerson().equalsIgnoreCase(emailList.get(i).getInterestedPerson())) {
                System.out.println("inside first if");
                if (Duration.between(emailList.get(i - 1).getTime(), emailList.get(i).getTime()).getSeconds() >= 60) {
                    ProfileDetails profileDetails = profileService.getMyProfile(emailList.get(i).getInterestedPerson());
                    profileDetails.getRecommended_list().add(profileService.getMyProfile(userEmail.email));
                    profileService.updateProfileDetails(profileDetails);
                    System.out.println("Time greater than 30");
                }
            }
        }
        Query query1 = new Query("DROP SERIES FROM influx WHERE userEmail='"+userEmail.email+"' ","telegraf");
        influxDB.query(query1);
    }
}
