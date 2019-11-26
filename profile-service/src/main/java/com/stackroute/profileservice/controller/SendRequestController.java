package com.stackroute.profileservice.controller;

import com.stackroute.profileservice.model.LikeProfileEmail;
import com.stackroute.profileservice.model.ProfileDetails;
import com.stackroute.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/sendRequest/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SendRequestController {
    private ProfileService profileService;

    @Autowired
    public SendRequestController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @PostMapping
    public ResponseEntity<?> addToSentRequestProfiles(@RequestBody LikeProfileEmail likeProfileEmail)
    {
        ProfileDetails profileDetails = profileService.getMyProfile(likeProfileEmail.getUser_email());
        List<String> sentRequestEmails = profileDetails.getSent_requests();
        Set<String> appendSet = new HashSet<>(sentRequestEmails);
        appendSet.add(likeProfileEmail.getLiked_email());
        sentRequestEmails = new ArrayList<>(appendSet);
        profileDetails.setSent_requests(sentRequestEmails);
        profileService.updateProfileDetails(profileDetails);


        //for other person it is added to received requests

        ProfileDetails profileDetails1 = profileService.getMyProfile(likeProfileEmail.getLiked_email());
        List<String> received_requests = profileDetails1.getReceived_requests();
        Set<String> appendSet1 = new HashSet<>(received_requests);
        appendSet1.add(likeProfileEmail.getUser_email());
        received_requests = new ArrayList<>(appendSet1);
        profileDetails1.setReceived_requests(received_requests);
        profileService.updateProfileDetails(profileDetails1);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
