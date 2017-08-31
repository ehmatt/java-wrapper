package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class TimeoutException extends OnePageException {

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException() {
        super();
    }
}
