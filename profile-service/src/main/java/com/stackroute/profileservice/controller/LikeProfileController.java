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
@RequestMapping("/likeProfile/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikeProfileController {

    private ProfileService profileService;

    @Autowired
    public LikeProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<?> addToLikedProfiles(@RequestBody LikeProfileEmail likeProfileEmail)
    {
        ProfileDetails profileDetails = profileService.getMyProfile(likeProfileEmail.getUser_email());
        List<String> liked_emails = profileDetails.getLiked_profiles();
        Set<String> appendSet = new HashSet<>(liked_emails);
        appendSet.add(likeProfileEmail.getLiked_email());
        liked_emails = new ArrayList<>(appendSet);
        profileDetails.setLiked_profiles(liked_emails);
        String likedEmail = likeProfileEmail.getLiked_email();
        List<ProfileDetails> recoList = profileDetails.getRecommended_list();
        for (ProfileDetails profile :
                recoList) {
            if(profile.getBasicPersonalDetails().getEmail().equals(likedEmail)){
                recoList.remove(profile);
            }
        }
        profileDetails.setRecommended_list(recoList);
        profileService.updateProfileDetails(profileDetails);


        //adding to liked by profiles
        ProfileDetails profileDetails1 = profileService.getMyProfile(likeProfileEmail.getLiked_email());
        List<String> liked_by_profiles_list = profileDetails1.getLiked_by_profiles();
        Set<String> appendSet1 = new HashSet<>(liked_by_profiles_list);
        appendSet1.add(likeProfileEmail.getUser_email());
        liked_by_profiles_list = new ArrayList<>(appendSet1);
        profileDetails1.setLiked_by_profiles(liked_by_profiles_list);
        profileService.updateProfileDetails(profileDetails1);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
