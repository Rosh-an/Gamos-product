package com.stackroute.profileservice.service;

import com.stackroute.profileservice.model.*;

import java.util.List;

public interface ProfileService {

    ProfileDetails saveProfileDetails(ProfileDetails profileDetails);

    List<ProfileDetails> searchbyParam(String str);

    ProfileDetails getMyProfile(String email);

    List<ProfileDetails> getProfiles(List<String> list);

    ProfileDetails updateProfileDetails(ProfileDetails profileDetails);

    void addtoAgeList(ProfileDetails profileDetails);
    void addtoCityList(ProfileDetails profileDetails);
    void addtoReligionList(ProfileDetails profileDetails);
    void addtoUserIdList(ProfileDetails profile);

    void addModifySubscription(String subscription,String email);

    void deleteMyProfile(String email);

}
