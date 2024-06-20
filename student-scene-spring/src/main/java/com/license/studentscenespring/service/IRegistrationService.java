package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.RegistrationDTO;
import com.license.studentscenespring.model.User;
import org.springframework.http.ResponseEntity;

public interface IRegistrationService {

    void registerUser(RegistrationDTO request) throws Exception;

}
