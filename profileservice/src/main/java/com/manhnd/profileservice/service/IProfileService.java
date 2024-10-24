package com.manhnd.profileservice.service;

import com.manhnd.profileservice.model.ProfileDTO;

import java.util.List;

public interface IProfileService {
    List<ProfileDTO> getAllProfiles();

    ProfileDTO createProfile(ProfileDTO profileDTO);

    ProfileDTO updateProfile(ProfileDTO profileDTO);

    ProfileDTO test(String node);
}
