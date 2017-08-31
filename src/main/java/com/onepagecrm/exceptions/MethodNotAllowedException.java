package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class MethodNotAllowedException extends APIException {

    public MethodNotAllowedException(String message) {
        super(message);
    }

    public MethodNotAllowedException() {
        super();
    }
}
