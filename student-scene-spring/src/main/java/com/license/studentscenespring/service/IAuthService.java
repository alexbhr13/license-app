package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.AuthRequestDTO;
import com.license.studentscenespring.dto.AuthResponseDTO;
import org.springframework.http.HttpHeaders;

public interface IAuthService {

    AuthResponseDTO checkUserCredentials(AuthRequestDTO request);

    String createToken(AuthResponseDTO response);

    HttpHeaders createHeader(String token);
}
