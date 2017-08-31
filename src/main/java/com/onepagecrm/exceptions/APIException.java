package com.onepagecrm.exceptions;

import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.net.Response;

@SuppressWarnings("WeakerAccess")
public class APIException extends OnePageException {

    public static final int TEAPOT_CODE = 418;

    private Response response;
    private final int timestamp;

    public APIException(String message) {
        super(message);
        timestamp = Utilities.getUnixTime();
    }

    public APIException() {
        timestamp = Utilities.getUnixTime();
    }

    public APIException setResponse(Response response) {
        this.response = response;
        return this;
    }

    public int getResponseCode() {
        return response != null ? response.getResponseCode() : TEAPOT_CODE;
    }

    public String getResponseMessage() {
        return response != null ? response.getResponseMessage() : "";
    }

    public String getResponseBody() {
        return response != null ? response.getResponseBody() : "";
    }

    public int getTimestamp() {
        return timestamp;
    }
}
