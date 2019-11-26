package com.stackroute.profileservice.service;

import com.stackroute.profileservice.model.ProfileDetails;
import com.stackroute.profileservice.model.RabbitMQObjectReceivedFromReco;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RabbitMQListener {

    @Autowired
    ProfileService profileService;

    @RabbitListener(queues = "${profile.queue.name}")
    public void receiveProfileObject(RabbitMQObjectReceivedFromReco rabbitMQObjectReceivedFromReco)
    {
        if(!rabbitMQObjectReceivedFromReco.getReco_email_list().isEmpty())
        {
            ProfileDetails profileDetails = profileService.getMyProfile(rabbitMQObjectReceivedFromReco.getEmail());
            Set<ProfileDetails> recoSet = new HashSet<>(profileDetails.getRecommended_list());
            recoSet.addAll(profileService.getProfiles(rabbitMQObjectReceivedFromReco.getReco_email_list()));
            recoSet.removeAll(profileService.getProfiles(profileDetails.getDisliked_profiles()));
            recoSet.removeAll(profileService.getProfiles(profileDetails.getLiked_profiles()));
            profileDetails.setRecommended_list(new ArrayList<>(recoSet));
            profileService.updateProfileDetails(profileDetails);
        }
    }
}
