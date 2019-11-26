package com.stackroute.profileservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Document(collection="profileDetails")
public class ProfileDetails {

    @Id
    private long id;

    @Transient
    public static final String SEQUENCE_NAME = "profile_sequence";

    private BasicPersonalDetails basicPersonalDetails;
    private FamilyDetails familyDetails;
    private HabitsDetails habitsDetails;
    private ProfessionalDetails professionalDetails;
    private HoroscopeDetails horoscopeDetails;
    private EducationalDetails educationalDetails;
    private boolean hasSubscribed;
    private String subscriptionPlan;
    private boolean hasProfessionalDetails;
    private boolean hasHoroscopeDetails;
    private boolean hasEducationalDetails;
    private boolean hasPersonalDetails;
    private boolean hasVideoUploaded;
    private String videoUrl;
    private String[] ImageUrl;
    private boolean hasDeactivated;
    private List<ProfileDetails> recommended_list;
    private List<String> sent_requests;
    private List<String> received_requests;
    private List<String> accepted_requests;
    private List<String> liked_profiles;
    private List<String> disliked_profiles;

    private List<String> liked_by_profiles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDetails that = (ProfileDetails) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
