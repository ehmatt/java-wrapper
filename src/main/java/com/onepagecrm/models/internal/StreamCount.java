package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.Map;

public class StreamCount implements Serializable {

    private Map<String, TeamCount> counts;

    @Override
    public String toString() {
        return counts.toString();
    }

    public Map<String, TeamCount> getCounts() {
        return counts;
    }

    public StreamCount setCounts(Map<String, TeamCount> counts) {
        this.counts = counts;
        return this;
    }
}
