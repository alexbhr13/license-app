package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.AuthRequestDTO;
import com.license.studentscenespring.dto.AuthResponseDTO;
import com.license.studentscenespring.security.JWTService;
import com.license.studentscenespring.service.AuthService;
import com.license.studentscenespring.service.IAuthService;
import com.license.studentscenespring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.support.ETagDoesntMatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final IAuthService authService;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

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

    @GetMapping("/validate")
    private ResponseEntity<Boolean> validateToken(@RequestHeader ("Authorization") String token) {
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractEmail(token));
            boolean isTokenValid = jwtService.isTokenValid(token,userDetails);
            if(!isTokenValid) throw new Exception();
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }
}
