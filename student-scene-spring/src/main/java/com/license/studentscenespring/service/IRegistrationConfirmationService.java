package com.license.studentscenespring.service;

public interface IRegistrationConfirmationService {
    void sendRegistrationConfirmationMail(String email) throws Exception;

    void confirmAccount(String theToken) throws Exception;

}
