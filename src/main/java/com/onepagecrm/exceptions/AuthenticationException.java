package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class AuthenticationException extends APIException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super();
    }
}
