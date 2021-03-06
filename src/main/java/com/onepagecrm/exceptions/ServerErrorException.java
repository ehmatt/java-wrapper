package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 18/08/2017.
 */
public class ServerErrorException extends APIException {

    private String responseBody;

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException() {

    }

    public String getResponseBody() {
        return responseBody;
    }

    public ServerErrorException setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        return this;
    }
}
