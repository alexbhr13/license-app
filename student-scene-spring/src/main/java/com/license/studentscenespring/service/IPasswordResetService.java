package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.PasswordRequestDTO;

public interface IPasswordResetService {

    void sendPasswordResetRequest(String email) throws Exception;

    void resetPassword(String token, PasswordRequestDTO request) throws Exception;


}
