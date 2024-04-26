package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.AuthRequestDTO;
import com.license.studentscenespring.dto.AuthResponseDTO;
import com.license.studentscenespring.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/auth/login")
    private ResponseEntity<String> loginUser(@RequestBody AuthRequestDTO request) {

        AuthResponseDTO response = authService.checkUserCredentials(request);
        if(response != null) {
            String token = authService.createToken(response);
            if(token != null) {
                HttpHeaders headers = authService.createHeader(token);
                return ResponseEntity.status(HttpStatus.OK).headers(headers).body("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }



}
