package com.license.studentscenespring.service;

import com.license.studentscenespring.model.User;
import com.license.studentscenespring.repository.TokenRepository;
import com.license.studentscenespring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.license.studentscenespring.model.Token;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationConfirmationService implements IRegistrationConfirmationService{

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenService tokenService;

    @Override
    public void sendRegistrationConfirmationMail(String email) throws Exception{
        try{
            User user = userRepository.findByEmail(email);
            if(user == null) {
                throw new Exception();
            }
            String token = UUID.randomUUID().toString();
            tokenService.createToken(user,token);
            String link ="http://localhost:8080/api/v1/auth/registration/confirm?token=" + token;
            emailService.send(buildEmail(user.getEmail(), link),user.getEmail(),"Confirmation mail");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void confirmAccount(String theToken) throws Exception {
        try {
            tokenService.validateToken(theToken);
        }catch (Exception e) {
            throw new Exception(e.getMessage()) ;
        }
        Token token = tokenRepository.findByToken(theToken);
        User user = token.getUser();
        user.set_confirmed(true);
        userRepository.save(user);
        token.setUsed(true);
        tokenRepository.save(token);
    }

    private String buildEmail(String name, String link) {
        return "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Confirm your account</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <p>Dear "+ name +",</p>\n" +
                "    <p>You have created an account on our platform. To confirm the registration, please click the link below:</p>\n" +
                "    <p>\n" +
                "      <a href=\""+link+"\" target=\"_blank\" style=\"display:inline-block;background-color:#3c8dbc;color:#fff;font-size:16px;padding:10px 20px;text-decoration:none;border-radius:5px;\">Confirm Account</a>\n" +
                "    </p>\n" +
                "    <p>If you did not create an account, please ignore this email.</p>\n" +
                "    <p>Best regards,</p>\n" +
                "    <p>StudentScene</p>\n" +
                "  </body>\n" +
                "</html>";
    }
}
