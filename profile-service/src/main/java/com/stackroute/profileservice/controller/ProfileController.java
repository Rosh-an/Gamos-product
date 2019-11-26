package com.stackroute.profileservice.controller;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.stackroute.profileservice.model.ProfileDetails;
import com.stackroute.profileservice.model.RabbitMQObjectToRecommendation;
import com.stackroute.profileservice.model.UserEmail;
import com.stackroute.profileservice.repository.ParamMessage;
import com.stackroute.profileservice.service.ProfileService;
import com.stackroute.profileservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/profile/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    @Inject
    private Environment environment;

    private ProfileService profileService;
    @Autowired
    public ProfileController(ProfileService profileService,RabbitMQSender rabbitMQSender) {
        this.profileService = profileService;
        this.rabbitMQSender=rabbitMQSender;
    }

   private RabbitMQSender rabbitMQSender;



    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

     List<String> emailList;

    @DeleteMapping("deleteProfile")
    public ResponseEntity<String> deleteMyProfile(@RequestBody UserEmail userEmail)
    {
        String email= userEmail.email;
        try {
            profileService.deleteMyProfile(email);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Delete Failed",HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("search")
    public ResponseEntity<List<ProfileDetails>> searchbyParam (@RequestBody ParamMessage paramMessage)
    {
       logger.info("Received String");
        if(paramMessage.getMessage().contains("bride") || paramMessage.getMessage().contains("groom")){
        return new ResponseEntity<>(profileService.searchbyParam(paramMessage.getMessage()), HttpStatus.ACCEPTED);}
        else
        {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
        }
    }

    @PostMapping(value="subscribe/{string}")
    public ResponseEntity<String> addSubscription(@PathVariable String string,@RequestBody UserEmail userEmail)
    {
        String email = userEmail.email;
        profileService.addModifySubscription(string,email);
        return new ResponseEntity<>("Subscription Successful",HttpStatus.ACCEPTED);
    }

    @PostMapping("myprofile")
    public ResponseEntity<ProfileDetails> getMyProfile(@RequestBody UserEmail userEmail)
    {
        String email= userEmail.email;
        return new ResponseEntity<>(profileService.getMyProfile(email),HttpStatus.OK);
    }

    @PostMapping("getrecommendations")
    public ResponseEntity<List<ProfileDetails>> getrecommendedProfiles(@RequestBody RabbitMQObjectToRecommendation toreco) throws InterruptedException {
      rabbitMQSender.send(toreco);
///timeout here
        Thread.sleep(20000);
       return new ResponseEntity<>(profileService.getMyProfile(toreco.getEmail()).getRecommended_list(),HttpStatus.ACCEPTED);
    }

    @PutMapping("updateProfile")
    public ResponseEntity<ProfileDetails> updateMyProfile(@RequestBody ProfileDetails profileDetails)
    {
        return new ResponseEntity<>(profileService.updateProfileDetails(profileDetails),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveProfile(@RequestBody ProfileDetails profileDetails)
    {
        try {
            profileService.saveProfileDetails(profileDetails);
        }catch (Exception e)
        {
           logger.info(e.getMessage());
        }
        return new ResponseEntity<>("saving please wait",HttpStatus.OK);
    }
}
