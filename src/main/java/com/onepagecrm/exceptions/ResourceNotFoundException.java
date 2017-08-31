package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class ResourceNotFoundException extends APIException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super();
    }
}
