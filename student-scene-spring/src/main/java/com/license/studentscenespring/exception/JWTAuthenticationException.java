package com.license.studentscenespring.exception;

public class JWTAuthenticationException extends RuntimeException{

    public JWTAuthenticationException(String message) {
        super(message);
    }
}
