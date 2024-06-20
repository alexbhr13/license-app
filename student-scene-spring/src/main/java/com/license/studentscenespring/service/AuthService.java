package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.AuthRequestDTO;
import com.license.studentscenespring.dto.AuthResponseDTO;
import com.license.studentscenespring.model.User;
import com.license.studentscenespring.repository.UserRepository;
import com.license.studentscenespring.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthResponseDTO checkUserCredentials(AuthRequestDTO request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email field is required");
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password field is required");
        }

        User userEntity = userRepository.findByEmail(request.getEmail());

        if(userEntity != null && passwordEncoder.matches(request.getPassword(),userEntity.getPassword())) {
            AuthResponseDTO response = new AuthResponseDTO();
            response.setEmail(userEntity.getEmail());
            response.setIsAdmin(userEntity.is_admin());
            return response;
        }
        return null;
    }

    public String createToken(AuthResponseDTO response) {
        User user = userRepository.findByEmail(response.getEmail());
            if (!user.is_confirmed()) {
                return null;
            }
        boolean isAdmin = response.getIsAdmin();
        return jwtService.generateToken(response.getEmail(), isAdmin);
    }

    public HttpHeaders createHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer "+token);
        return headers;
    }

}
