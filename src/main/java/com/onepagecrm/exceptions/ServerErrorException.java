package com.onepagecrm.exceptions;

public class ServerErrorException extends APIException {

    private String responseBody;

    public String getResponseBody() {
        return responseBody;
    }

    public ServerErrorException setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        return this;
    }
}
