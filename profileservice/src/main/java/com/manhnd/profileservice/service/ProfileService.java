package com.manhnd.profileservice.service;

import com.manhnd.commonservice.common.CommonException;
import com.manhnd.profileservice.data.Profile;
import com.manhnd.profileservice.event.EventProducer;
import com.manhnd.profileservice.model.ProfileDTO;
import com.manhnd.profileservice.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.manhnd.profileservice.utils.constant.ProfileConstant.STATUS_PROFILE_PENDING;


@Service
@Slf4j
public class ProfileService implements IProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public List<ProfileDTO> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileDTO> profileDTOS = profiles.stream()
                .map(ProfileDTO::entityToDTO)
                .collect(Collectors.toList());
        return profileDTOS;
    }

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Optional<Profile> profileOp = profileRepository.findByEmail(profileDTO.getEmail());
        if (profileOp.isPresent()) {
            throw new CommonException("Email đã tồn tại");
        }
        profileDTO.setStatus(STATUS_PROFILE_PENDING);
        profileDTO.setInitalBalance(0.0);
        Profile profile = ProfileDTO.dtoToEntity(profileDTO);
        Profile profileSave = profileRepository.save(profile);
        eventProducer.sendProfileCreatedEvent(profileDTO, profileSave.getId());
        return ProfileDTO.entityToDTO(profileSave);
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) {
        Optional<Profile> profileOp = profileRepository.findById(profileDTO.getId());
        if (profileOp.isEmpty()) {
            throw new CommonException("Không tìm thấy profile");
        }
        Profile profile = profileOp.get();
        if (StringUtils.isNotBlank(profileDTO.getEmail())) {
            profile.setEmail(profileDTO.getEmail());
        }
        if (StringUtils.isNotBlank(profileDTO.getName())) {
            profile.setName(profileDTO.getName());
        }
        if (StringUtils.isNotBlank(profileDTO.getRole())) {
            profile.setRole(profileDTO.getRole());
        }
        if (StringUtils.isNotBlank(profileDTO.getStatus())) {
            profile.setStatus(profileDTO.getStatus());
        }

        Profile profileSave = profileRepository.save(profile);
        if (profileSave == null) {
            throw new CommonException("Cập nhật thất bại");
        }
        return ProfileDTO.entityToDTO(profileSave);
    }

}
