package com.stackroute.profileservice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class ProfileModelListener extends AbstractMongoEventListener {

    private GenerateSequence sequenceGeneratorService;

    @Autowired
    public ProfileModelListener(GenerateSequence sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent event) {
        if(event.getSource().getClass()==ProfileDetails.class) {
            ProfileDetails profileDetails = (ProfileDetails) event.getSource();
            if (profileDetails.getId() < 1) {
                profileDetails.setId(sequenceGeneratorService.generateSequence(ProfileDetails.SEQUENCE_NAME));
            }
        }

    }
}
