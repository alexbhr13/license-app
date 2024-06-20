package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.PasswordRequestDTO;
import com.license.studentscenespring.service.IPasswordResetService;
import com.license.studentscenespring.service.ITokenService;
import com.license.studentscenespring.service.PasswordResetService;
import com.license.studentscenespring.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class PasswordResetController {

    private final IPasswordResetService passwordResetService;
    private final ITokenService tokenService;

    @PostMapping("/pwdres/request")
    public ResponseEntity<String> sendPasswordResetRequest(@RequestHeader String email) {
        try{
            passwordResetService.sendPasswordResetRequest(email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Email has been sent successfully");
    }

    @PostMapping("/pwdres/receive")
    public ResponseEntity<String> receivePasswordResetRequest(@RequestParam("token") String token) {
        try{
            tokenService.validateToken(token);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Password reset token validated");
    }

    @PostMapping("/pwdres/reset")
    public ResponseEntity<String> resetPassword(@RequestHeader String token, @RequestBody PasswordRequestDTO request) {
        try {
            passwordResetService.resetPassword(token, request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Password has been reset");
    }



}
