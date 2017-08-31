package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class PreconditionFailedException extends APIException {

    public PreconditionFailedException(String message) {
        super(message);
    }

    public PreconditionFailedException() {
        super();
    }
}
