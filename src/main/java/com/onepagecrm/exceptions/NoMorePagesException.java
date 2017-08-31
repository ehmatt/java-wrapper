package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class NoMorePagesException extends OnePageException {

    public NoMorePagesException(String message) {
        super(message);
    }

    public NoMorePagesException() {
        super();
    }
}
