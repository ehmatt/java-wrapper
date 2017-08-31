package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class BadRequestException extends APIException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super();
    }
}
