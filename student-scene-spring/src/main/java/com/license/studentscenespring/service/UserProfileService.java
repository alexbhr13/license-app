package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.UserProfileDTO;
import com.license.studentscenespring.model.User;
import com.license.studentscenespring.model.UserProfile;
import com.license.studentscenespring.repository.UserProfileRepository;
import com.license.studentscenespring.repository.UserRepository;
import com.license.studentscenespring.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService implements IUserProfileService{

    private final UserRepository userRepository;
    private final JWTService jwtService;

    public void setUserProfile(UserProfileDTO userProfileDTO, String token) {
        User user = userRepository.findByEmail(jwtService.extractEmail(token));
        user.setUserProfile(userProfileFromDTO(user,userProfileDTO));
        userRepository.saveAndFlush(user);
    }

    public UserProfileDTO getUserProfile(String token) {
        String email = jwtService.extractEmail(token);
        User user = userRepository.findByEmail(email);
        UserProfile userProfile = user.getUserProfile();
        return userProfileToDTO(userProfile,user);
    }

    private UserProfile userProfileFromDTO(User user, UserProfileDTO userProfileDTO) {
        return UserProfile.builder()
                .id(userProfileDTO.getId())
                .birthDate(userProfileDTO.getBirthDate())
                .duration(userProfileDTO.getDuration())
                .firstName(userProfileDTO.getFirstName())
                .lastName(userProfileDTO.getLastName())
                .photoURL(userProfileDTO.getPhotoURL())
                .academicYearStartDate(userProfileDTO.getAcademicYearStartDate())
                .universityName(userProfileDTO.getUniversityName())
                .user(user)
                .build();
    }

    private UserProfileDTO userProfileToDTO(UserProfile userProfile, User user) {
        return UserProfileDTO.builder()
                .id(userProfile.getId())
                .userId(user.getUserProfile().getId())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .photoURL(userProfile.getPhotoURL())
                .birthDate(userProfile.getBirthDate())
                .academicYearStartDate(userProfile.getAcademicYearStartDate())
                .universityName(userProfile.getUniversityName())
                .duration(userProfile.getDuration())
                .build();
    }
}
