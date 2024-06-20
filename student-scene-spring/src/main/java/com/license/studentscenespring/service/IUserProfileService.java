package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.UserProfileDTO;

public interface IUserProfileService {
    void setUserProfile(UserProfileDTO userProfileDTO, String token);

    UserProfileDTO getUserProfile(String token);
}
