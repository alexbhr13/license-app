package com.license.studentscenespring.service;

import com.license.studentscenespring.model.User;
import com.license.studentscenespring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RegistrationConfirmationService registrationConfirmationService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserDetails> userDetailsOptional = Optional.ofNullable(userRepository.findByEmail(email));
        return userDetailsOptional
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void signUpUser(User user) throws Exception {
        try{
            if(isUserByEmailConfirmed(user.getEmail())) throw new Exception("Email already taken");

            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.saveAndFlush(user);
            registrationConfirmationService.sendRegistrationConfirmationMail(user.getEmail());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean isUserByEmailConfirmed(String email) {
        List<User> users = userRepository.findAllByEmail(email);
        return users.stream()
                .anyMatch(User::is_confirmed);
    }
}
