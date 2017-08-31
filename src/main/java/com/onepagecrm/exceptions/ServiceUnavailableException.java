package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class ServiceUnavailableException extends APIException {

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException() {
        super();
    }
}
