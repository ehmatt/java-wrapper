package com.onepagecrm.models;

import java.io.Serializable;

/**
 * Created by Nichollas on 27/10/17.
 */
public class StartupObject implements Serializable {

    private final boolean fullResponse;
    private final User user;
    private final ContactList actionStream;
    private final ContactList contacts;
    private final DealList deals;

    public StartupObject(User user, boolean fullResponse) {
        this.fullResponse = fullResponse;
        this.user = user;
        actionStream = null;
        contacts = null;
        deals = null;
    }

    public StartupObject(User user, ContactList actionStream, ContactList contacts, DealList deals, boolean fullResponse) {
        this.fullResponse = fullResponse;
        this.user = user;
        this.actionStream = actionStream;
        this.contacts = contacts;
        this.deals = deals;
    }

    public User getUser() {
        return user;
    }

    public ContactList getActionStream() {
        return actionStream;
    }

    public ContactList getContacts() {
        return contacts;
    }

    public DealList getDeals() {
        return deals;
    }

    public boolean isFullResponse() {
        return fullResponse;
    }
}
