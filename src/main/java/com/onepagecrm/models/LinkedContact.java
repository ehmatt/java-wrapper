package com.onepagecrm.models;

import java.io.Serializable;

/**
 * Created by Nichollas on 08/03/17.
 */
public class LinkedContact extends BaseResource implements Serializable {

    private String contactId;
    private String linkedWithId;
    private String companyId;

    public LinkedContact(String contactId, String linkedWithId, String companyId) {
        this.contactId = contactId;
        this.linkedWithId = linkedWithId;
        this.companyId = companyId;
    }

    public LinkedContact() {

    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getLinkedWithId() {
        return linkedWithId;
    }

    public void setLinkedWithId(String linkedWithId) {
        this.linkedWithId = linkedWithId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public boolean isValid() {
        return contactId != null && !contactId.isEmpty() &&
                linkedWithId != null && !linkedWithId.isEmpty() &&
                companyId != null && !companyId.isEmpty();
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }
}
