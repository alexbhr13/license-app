package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.RegistrationDTO;
import com.license.studentscenespring.service.IRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class RegistrationController {
    private final IRegistrationService registrationService;

    @PostMapping("/auth/registration")
    public ResponseEntity<String> register(@RequestBody RegistrationDTO request) {
        try{
            registrationService.registerUser(request);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("User created");
    }

}
