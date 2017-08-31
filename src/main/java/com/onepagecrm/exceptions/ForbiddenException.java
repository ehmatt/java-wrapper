package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class ForbiddenException extends APIException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException() {
        super();
    }
}
