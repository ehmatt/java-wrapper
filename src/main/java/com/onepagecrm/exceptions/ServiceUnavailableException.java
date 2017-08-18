package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 20/04/2017.
 */
public class ServiceUnavailableException extends APIException {

    private int timestamp;

    public int getTimestamp() {
        return timestamp;
    }

    public ServiceUnavailableException setTimestamp(int timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
