package com.manhnd.profileservice.service;

import com.manhnd.profileservice.model.ProfileDTO;

import java.util.List;

public interface IProfileService {
    List<ProfileDTO> getAllProfiles();

    ProfileDTO createProfile(ProfileDTO profileDTO);
}
