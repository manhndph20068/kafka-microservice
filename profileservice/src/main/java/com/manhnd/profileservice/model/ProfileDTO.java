package com.manhnd.profileservice.model;

import com.manhnd.profileservice.data.Profile;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {

    private String email;

    private String name;

    private String status;

    private String role;

    private double initalBalance;

    public static Profile dtoToEntity(ProfileDTO profileDTO) {
        return Profile.builder()
                .email(profileDTO.getEmail())
                .name(profileDTO.getName())
                .status(profileDTO.getStatus())
                .role(profileDTO.getRole())
                .build();
    }

    public static ProfileDTO entityToDTO(Profile profile) {
        return ProfileDTO.builder()
                .email(profile.getEmail())
                .name(profile.getName())
                .status(profile.getStatus())
                .role(profile.getRole())
                .build();
    }
}
