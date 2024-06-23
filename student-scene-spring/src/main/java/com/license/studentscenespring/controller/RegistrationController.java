package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.RegistrationDTO;
import com.license.studentscenespring.service.IRegistrationConfirmationService;
import com.license.studentscenespring.service.IRegistrationService;
import com.license.studentscenespring.service.RegistrationConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class RegistrationController {
    private final IRegistrationService registrationService;
    private final IRegistrationConfirmationService registrationConfirmationService;
    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody RegistrationDTO request) {
        Map<String, String> response = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        try{
            registrationService.registerUser(request);
        }catch (Exception e) {
            response.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(response);
        }
        response.put("message","User created");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @GetMapping("/registration/confirm")
    public ResponseEntity<String> confirmAccount(@RequestParam("token") String token) {
        try{
            registrationConfirmationService.confirmAccount(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }return ResponseEntity.status(HttpStatus.OK).body("Account validated");
    }

}
