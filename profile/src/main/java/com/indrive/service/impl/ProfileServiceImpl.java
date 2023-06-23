package com.indrive.service.impl;

import com.indrive.entity.Location;
import com.indrive.entity.Profile;
import com.indrive.repository.ProfileRepo;
import com.indrive.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepo.save(profile);
    }

    @Override
    public Optional<Profile> getProfileByEmailId(String emailId) {
        return profileRepo.findById(emailId);
    }

    @Override
    public Profile updateProfileByEmailId(@RequestBody Profile profile) {
        String emailId = profile.getEmailId();
        Optional<Profile> existingData = profileRepo.findById(emailId);
        if (existingData.isPresent()) {
            Profile updatingProfile = Profile.builder()
                    .emailId(emailId).name(profile.getName())
                    .password(profile.getPassword()).rating(profile.getRating())
                    .type(profile.getType())
                    .location(Location.builder()
                            .locationId(existingData.get().getLocation().getLocationId())
                            .longitude(profile.getLocation().getLongitude())
                            .latitude(profile.getLocation().getLatitude())
                            .build()).build();
            profileRepo.save(updatingProfile);
            return updatingProfile;
        } else {
            throw new NoSuchElementException("CrawledData with id " + emailId + " not found");
        }
    }


}
