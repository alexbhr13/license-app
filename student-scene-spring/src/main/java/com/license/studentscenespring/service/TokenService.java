package com.license.studentscenespring.service;

import com.license.studentscenespring.model.Token;
import com.license.studentscenespring.model.User;
import com.license.studentscenespring.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService implements ITokenService{

    private final TokenRepository tokenRepository;

    public void validateToken(String theToken) throws Exception{
        final Token token = tokenRepository.findByToken(theToken);
        if(isTokenFound(token)) {
            if(isTokenExpired(token)) {
                if(isTokenUsed(token)) {
                    throw new Exception("Token is invalid or has expired");
                }
            } else throw new Exception("Token is invalid or has expired");
        } else throw new Exception("Token is invalid or has expired");

    }

    public void createToken(User user, String theToken) {
        Token token = new Token(theToken,user);
        tokenRepository.save(token);
    }


    private boolean isTokenUsed(Token token) {
        return token.isUsed();
    }

    private boolean isTokenFound(Token token) {
        return token != null;
    }

    private boolean isTokenExpired(Token token) {
        return token.isTokenExpired();
    }
}
