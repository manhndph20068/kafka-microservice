package com.manhnd.profileservice.controller;

import com.manhnd.commonservice.model.ServiceResult;
import com.manhnd.profileservice.model.ProfileDTO;
import com.manhnd.profileservice.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    @Autowired
    private IProfileService profileService;

    @GetMapping
    public ResponseEntity<ServiceResult<List<ProfileDTO>>> getAllProfiles() {
        return ServiceResult.okEntity(profileService.getAllProfiles());
    }

    @PostMapping
    public ResponseEntity<ServiceResult<ProfileDTO>> createProfile(@RequestBody ProfileDTO profileDTO) {
        return ServiceResult.okEntity(profileService.createProfile(profileDTO));
    }

}
