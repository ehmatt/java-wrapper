package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.List;

public class ContactsCount implements Serializable {

    private List<CountMap> counts;

    public List<CountMap> getCounts() {
        return counts;
    }

    public ContactsCount setCounts(List<CountMap> counts) {
        this.counts = counts;
        return this;
    }
}