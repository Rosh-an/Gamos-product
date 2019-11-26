package com.stackroute.profileservice.controller;

import com.stackroute.profileservice.model.LikeProfileEmail;
import com.stackroute.profileservice.model.ProfileDetails;
import com.stackroute.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acceptProfile/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AcceptRequestController {
    private ProfileService profileService;

    @Autowired
    public AcceptRequestController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<?> acceptRequests(@RequestBody LikeProfileEmail likeProfileEmail)
    {
        ProfileDetails profileDetails = profileService.getMyProfile(likeProfileEmail.getUser_email());
        List<String> received_requests= profileDetails.getReceived_requests();
        received_requests.remove(likeProfileEmail.getLiked_email());
        profileDetails.setReceived_requests(received_requests);
        List<String> accepted_requests = profileDetails.getAccepted_requests();
        accepted_requests.add(likeProfileEmail.getLiked_email());
        profileDetails.setAccepted_requests(accepted_requests);
        profileService.updateProfileDetails(profileDetails);

        ProfileDetails profileDetails1=profileService.getMyProfile(likeProfileEmail.getLiked_email());
        List<String> sent_requests = profileDetails.getSent_requests();
        sent_requests.remove(likeProfileEmail.getUser_email());
        profileDetails1.setSent_requests(sent_requests);
        List<String> accepted_requests1 = profileDetails1.getAccepted_requests();
        accepted_requests1.add(likeProfileEmail.getUser_email());
        profileDetails1.setAccepted_requests(accepted_requests1);
        profileService.updateProfileDetails(profileDetails1);


        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
