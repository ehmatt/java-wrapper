package com.onepagecrm.models;

import java.io.Serializable;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

/**
 * Created by Nichollas on 08/03/17.
 */
public class LinkedContact extends BaseResource implements Serializable {

    private String contactId;
    private String linkedWithId;
    private String companyId;

    public LinkedContact() {

    }

    public String getContactId() {
        return contactId;
    }

    public LinkedContact setContactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    public String getLinkedWithId() {
        return linkedWithId;
    }

    public LinkedContact setLinkedWithId(String linkedWithId) {
        this.linkedWithId = linkedWithId;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public LinkedContact setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public boolean isValid() {
        return notNullOrEmpty(contactId) &&
                notNullOrEmpty(linkedWithId) &&
                notNullOrEmpty(companyId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedContact that = (LinkedContact) o;
        if (!this.isValid() || !that.isValid()) return false;
        return this.contactId.equals(that.contactId) && this.linkedWithId.equals(that.linkedWithId) && this.companyId.equals(that.companyId);
    }

    @Override
    public String toString() {
        return "LinkedContact{" +
                "contactId='" + contactId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", linkedWithId='" + linkedWithId + '\'' +
                '}';
    }
}
