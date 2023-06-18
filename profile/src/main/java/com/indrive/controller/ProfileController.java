package com.indrive.controller;

import com.indrive.entity.Profile;
import com.indrive.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/")
    public Profile saveProfile(@RequestBody Profile profile){
        return profileService.saveProfile(profile);

    }

    @GetMapping("/")
    public Optional<Profile> getProfileByEmailId(@RequestParam String emailId){
        return profileService.getProfileByEmailId(emailId);
    }


    @PutMapping("/")
    public Profile updateProfileByEmailId(@RequestBody Profile profile) throws NoSuchElementException {
        return profileService.updateProfileByEmailId(profile);
    }






}
