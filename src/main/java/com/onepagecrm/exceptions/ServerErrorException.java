package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class ServerErrorException extends APIException {

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException() {
        super();
    }
}
