package com.openapi.exception;

/**
 * @author sourabh
 * @implNote Exception class for authentication service
 */
public class AuthenticationException extends Exception {

    /**
     * Generated Serial Version ID
     */
    private static final long serialVersionUID = 89219759617922093L;

    public AuthenticationException(String msg) {
        super(msg);
    }
}
