package com.onepagecrm.models;

import java.io.Serializable;

/**
 * Created by Nichollas on 27/10/17.
 */
public class LoginResultObject implements Serializable {

    private final boolean fullResponse;
    private final User user;
    private final ContactList actionStream;

    public LoginResultObject(User user, boolean fullResponse) {
        this.fullResponse = fullResponse;
        this.user = user;
        actionStream = null;
    }

    public LoginResultObject(User user, ContactList actionStream, boolean fullResponse) {
        this.fullResponse = fullResponse;
        this.user = user;
        this.actionStream = actionStream;
    }

    public User getUser() {
        return user;
    }

    public ContactList getActionStream() {
        return actionStream;
    }

    public boolean isFullResponse() {
        return fullResponse;
    }
}
