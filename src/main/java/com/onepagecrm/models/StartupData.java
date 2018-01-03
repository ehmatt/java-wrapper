package com.onepagecrm.models;

import java.io.Serializable;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

/**
 * Created by Nichollas on 27/10/17.
 */
public class StartupData implements Serializable {

    private final boolean fullResponse;
    private final String endpointUrl;
    private final User user;
    private final ContactList actionStream;
    private final ContactList contacts;
    private final DealList deals;

    public StartupData(String endpointUrl, User user) {
        this.fullResponse = false;
        this.endpointUrl = endpointUrl;
        this.user = user;
        this.actionStream = null;
        this.contacts = null;
        this.deals = null;
    }

    public StartupData(String endpointUrl, User user, ContactList actionStream, ContactList contacts, DealList deals) {
        this.fullResponse = true;
        this.endpointUrl = endpointUrl;
        this.user = user;
        this.actionStream = actionStream;
        this.contacts = contacts;
        this.deals = deals;
    }

    public boolean isValid() {
        return notNullOrEmpty(endpointUrl) && user != null;
    }

    public boolean isFullResponse() {
        return fullResponse;
    }

    public String getEndpointUrl() {
        return endpointUrl;
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
}
