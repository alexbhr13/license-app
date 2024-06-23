package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.AuthRequestDTO;
import com.license.studentscenespring.dto.AuthResponseDTO;
import com.license.studentscenespring.service.AuthService;
import com.license.studentscenespring.service.IAuthService;
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
public class AuthController {


    private final IAuthService authService;

    @PostMapping("/login")
    private ResponseEntity<Map<String,String>> loginUser(@RequestBody AuthRequestDTO request) {
        HttpHeaders headers;
        Map<String,String> response = new HashMap<>();
        AuthResponseDTO dto = authService.checkUserCredentials(request);
        if(dto != null) {
            String token = authService.createToken(dto);
            if(token != null) {
                headers = authService.createHeader(token);
                response.put("message", "user logged in");
                return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
            } else {
                response.put("message","token is null");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }
        response.put("message","login error");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
