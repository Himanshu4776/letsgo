package com.indrive.service;

import com.indrive.entity.Profile;

import java.util.Optional;

public interface ProfileService {

    Profile saveProfile(Profile profile);

    Optional<Profile> getProfileByEmailId(String emailId);

    Profile updateProfileByEmailId(Profile profile);
}
