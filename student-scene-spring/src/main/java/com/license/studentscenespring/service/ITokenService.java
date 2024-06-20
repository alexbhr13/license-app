package com.license.studentscenespring.service;

public interface ITokenService {

    void validateToken(String theToken) throws Exception;
}
