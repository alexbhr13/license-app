package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.RegistrationDTO;
import com.license.studentscenespring.model.Cart;
import com.license.studentscenespring.model.Favorites;
import com.license.studentscenespring.model.User;
import com.license.studentscenespring.model.UserProfile;
import com.license.studentscenespring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService{

    private final EmailValidatorService emailValidatorService;
    private final PasswordValidatorService passwordValidatorService;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public void registerUser(RegistrationDTO request) throws Exception {
        if(!emailValidatorService.test(request.getEmail())) throw new Exception("Email is invalid");
        if(!passwordValidatorService.test(request.getPassword())) throw new Exception("Password is invalid");
        if(!userRepository.findAllByEmail(request.getEmail()).isEmpty()) throw new Exception("User already exists");
        userService.signUpUser(
                User.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .is_admin(request.is_admin())
                        .userProfile(new UserProfile())
                        .favorites(new Favorites())
                        .cart(new Cart())
                        .build()
        );
    }
}
