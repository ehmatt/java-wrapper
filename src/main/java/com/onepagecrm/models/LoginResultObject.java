package com.onepagecrm.models;

import java.io.Serializable;

/**
 * Created by Nichollas on 27/10/17.
 */
public class LoginResultObject implements Serializable {

    private final boolean fullResponse;
    private final User user;
    private final ContactList contacts;

    public LoginResultObject(User user, boolean fullResponse) {
        this.fullResponse = fullResponse;
        this.user = user;
        contacts = null;
    }

    public LoginResultObject(User user, ContactList contacts, boolean fullResponse) {
        this.fullResponse = fullResponse;
        this.user = user;
        this.contacts = contacts;
    }

    public User getUser() {
        return user;
    }

    public ContactList getContacts() {
        return contacts;
    }

    public boolean isFullResponse() {
        return fullResponse;
    }
}
