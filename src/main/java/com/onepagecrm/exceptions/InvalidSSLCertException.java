package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 08/09/2017.
 */
public class InvalidSSLCertException extends OnePageException {

    public InvalidSSLCertException(String message) {
        super(message);
    }

    public InvalidSSLCertException() {
        super();
    }
}
