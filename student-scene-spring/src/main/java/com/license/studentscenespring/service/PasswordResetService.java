package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.PasswordRequestDTO;
import com.license.studentscenespring.model.Token;
import com.license.studentscenespring.model.User;
import com.license.studentscenespring.repository.TokenRepository;
import com.license.studentscenespring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService implements IPasswordResetService{

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final PasswordValidatorService passwordValidatorService;
    private final BCryptPasswordEncoder passwordEncoder;


    public void sendPasswordResetRequest(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("Email has been sent successfully");
        }
        String token = UUID.randomUUID().toString();
        tokenService.createToken(user,token);
        String link = "http://localhost:4200/pwdres-reset?token=" + token;
        emailService.send(buildEmail(user.getEmail(),link),user.getEmail(),"Password reset request");
    }

    public void resetPassword(String token, PasswordRequestDTO request) throws Exception {
        try{
            tokenService.validateToken(token);
            validateNewPassword(request.getNewPassword());
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        Token passwordResetToken = tokenRepository.findByToken(token);
        User user = passwordResetToken.getUser();
        changeUserPassword(user,request.getNewPassword());
        passwordResetToken.setUsed(true);
        tokenRepository.save(passwordResetToken);
    }

    private void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
    private void validateNewPassword(String newPassword) throws Exception {
        boolean isValidPassword = passwordValidatorService.test(newPassword);
        if(!isValidPassword) {
            throw new Exception("Password not valid, ensure at least one lowercase letter," +
                    " one uppercase letter, one digit, at least 8 characters, and a special character");
        }
    }

    private String buildEmail(String name, String link) {
        return "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Reset your password</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <p>Dear "+ name +",</p>\n" +
                "    <p>We received a request to reset your password. To reset your password, click the button below:</p>\n" +
                "    <p>\n" +
                "      <a href=\""+link+"\" target=\"_blank\" style=\"display:inline-block;background-color:#3c8dbc;color:#fff;font-size:16px;padding:10px 20px;text-decoration:none;border-radius:5px;\">Reset Password</a>\n" +
                "    </p>\n" +
                "    <p>If you did not request a password reset, please ignore this email.</p>\n" +
                "    <p>Best regards,</p>\n" +
                "    <p>[your company name]</p>\n" +
                "  </body>\n" +
                "</html>";
    }

}
