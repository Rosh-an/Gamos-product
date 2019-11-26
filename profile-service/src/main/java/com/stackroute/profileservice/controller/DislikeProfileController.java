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
@RequestMapping("/dislikeProfile/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DislikeProfileController {
    private ProfileService profileService;

    @Autowired
    public DislikeProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<?> addToDislikeProfiles(@RequestBody LikeProfileEmail likeProfileEmail)
    {
        ProfileDetails profileDetails = profileService.getMyProfile(likeProfileEmail.getUser_email());
        List<String> dislikedEmails = profileDetails.getDisliked_profiles();
        dislikedEmails.add(likeProfileEmail.getLiked_email());
        profileDetails.setDisliked_profiles(dislikedEmails);
        List<String> disliked_emails = profileDetails.getDisliked_profiles();
        Set<String> appendSet = new HashSet<>(disliked_emails);
        appendSet.add(likeProfileEmail.getLiked_email());
        disliked_emails = new ArrayList<>(appendSet);
        profileDetails.setDisliked_profiles(disliked_emails);
        List<ProfileDetails> reco_list = profileDetails.getRecommended_list();
        reco_list.remove(profileService.getMyProfile(likeProfileEmail.getLiked_email()));
        profileDetails.setRecommended_list(reco_list);
        profileService.updateProfileDetails(profileDetails);
        String dislikedEmail = likeProfileEmail.getLiked_email();
        List<ProfileDetails> recoList = profileDetails.getRecommended_list();
        for (ProfileDetails profile :
                recoList) {
            if(profile.getBasicPersonalDetails().getEmail().equals(dislikedEmail)){
                recoList.remove(profile);
            }
        }
        profileDetails.setRecommended_list(recoList);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
