package com.onepagecrm.exceptions;

import com.onepagecrm.models.internal.Utilities;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/08/2017.
 */
public class OnePageException extends Exception {

    private String message;
    private final int timestamp;
    private String errorMessage;

    public OnePageException(String message) {
        super(message);
        this.message = message;
        this.timestamp = Utilities.getUnixTime();
    }

    public OnePageException() {
        this.timestamp = Utilities.getUnixTime();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public OnePageException setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public OnePageException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
