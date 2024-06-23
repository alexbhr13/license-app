package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.UserProfileDTO;
import com.license.studentscenespring.service.IUserProfileService;
import com.license.studentscenespring.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(path = "api/v1/profile")
@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final IUserProfileService userProfileService;

    @PostMapping("/setProfile")
    public ResponseEntity<?> setUserProfile(
            @RequestBody UserProfileDTO userProfileDTO,
            @RequestHeader ("Authorization") String token) {
        try {
            userProfileService.setUserProfile(userProfileDTO, token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("User profile configured");
    }

    @GetMapping("/getProfile")
    public ResponseEntity<?> getUserProfile(@RequestHeader ("Authorization") String token) {
        try {
            UserProfileDTO userProfileDTO = userProfileService.getUserProfile(token);
            return ResponseEntity.status(HttpStatus.OK).body(userProfileDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
