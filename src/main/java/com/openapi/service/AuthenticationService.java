package com.openapi.service;

import com.openapi.exception.AuthenticationException;

/**
 * @author sourabh
 * @implNote service class for interacting with authentication service
 */
public interface AuthenticationService {

    /**
     * Method will validate Token else throw exception in case of invalid token
     *
     * @param authToken
     * @throws AuthenticationException
     */
    void validateAuthorisation(String authToken) throws AuthenticationException;


}
