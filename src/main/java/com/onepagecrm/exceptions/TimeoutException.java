package com.onepagecrm.exceptions;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 18/08/2017.
 */
public class TimeoutException extends OnePageException {

    private int timeMs;

    public int getTimeMs() {
        return timeMs;
    }

    public TimeoutException setTimeMs(int timeMs) {
        this.timeMs = timeMs;
        return this;
    }
}
