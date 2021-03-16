package com.openapi.service.implementation;

import com.openapi.exception.AuthenticationException;
import com.openapi.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AuthenticationServiceImplTest {

    AuthenticationService authenticationService = new AuthenticationServiceImpl();

    private final String validToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJqYW5lZG9lQGV4YW1wbGUuY29tIiwibmFtZSI6IkphbmUgRG9lIiwiaWF0IjoxNTQ2MzAwODAwLCJleHAiOjE4OTM0NTYwMDB9.Csjq0GWRu5ChiEjNhzyDDuk9YR08gIwvnsfTkqoOISPb9ZEJcFGs6yrEZZ5lhyIneW-QIJZjqg8EPHaitFd6PncOb1zuFBcECK1aPNiVXQ1stS4GZZ0K7kMTfbn_bDtg3LwkScRLvd1UaXPX3gdWiyTWU_EQeBtpaKvN_NtqzRlAVslmHfsi50dgDmg0Pbk80oLqbN8TB4JctgM6VuvI5yCrwyID9GNwItSMVpd1ZjOmGTiof2hqi6E3KH3j9RfWz_Gq4Vq61XvYKEooDzDjuFSoie1vkptVp2fngRMBTxTTM2ZBCEpd_HbcrqBiHuIMw19nyXr8mP7n8rPLG4S1LA";
    private final String invalidToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJqYW5lZG9lQGV4YW1wbGUuY29tIiwibmFtZSI6IkphbmUgRG9lIiwiaWF0IjoxNTQ2MzAwODAwLCJleHAiOjE4OTM0NTYwMDB9.Csjq0GWRu5ChiEjNhzyDDuk9YR08gIwvnsfTkqoOISPb9ZEJcFGs6yrEZZ5lhyIneW-QIJZjqg8EPHaitFd6PncOb1zuFBcECK1aPNiVXQ1stS4GZZ0K7kMTfbn_bDtg3LwkScRLvd1UaXPX3gdWiyTWU_EQeBtpaKvN_NtqzRlAVslmHfsi50dgDmg0Pbk80oLqbN8TB4JctgM6VuvI5yCrwyID9GNwItSMVpd1ZjOmGTiof2hqi6E3KH3j9RfWz_Gq4Vq61XvinvalidSoie1vkptVp2fngRMBTxTTM2ZBCEpd_HbcrqBiHuIMw19nyXr8mP7n8rPLG4S1LA";

    @Test
    void validateAuthorisationWithValidToken() {

        Assertions.assertDoesNotThrow(() -> authenticationService.validateAuthorisation(validToken));

    }

    @Test
    void validateAuthorisationWithInValidToken() {

        Assertions.assertThrows(AuthenticationException.class, () -> authenticationService.validateAuthorisation(invalidToken));

    }

    @Test
    void validateAuthorisationWithEmptyToken() {

        Assertions.assertThrows(AuthenticationException.class, () -> authenticationService.validateAuthorisation(null));

        Assertions.assertThrows(AuthenticationException.class, () -> authenticationService.validateAuthorisation(""));

    }

}