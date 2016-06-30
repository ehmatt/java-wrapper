package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.Map;

public class ContactsCount implements Serializable {

    private Map<String, CountMap> counts;

    @Override
    public String toString() {
        return counts.toString();
    }

    public Map<String, CountMap> getCounts() {
        return counts;
    }

    public ContactsCount setCounts(Map<String, CountMap> counts) {
        this.counts = counts;
        return this;
    }
}