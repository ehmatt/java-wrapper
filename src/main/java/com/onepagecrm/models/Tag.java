package com.onepagecrm.models;

import com.onepagecrm.models.serializer.TagSerializer;

import java.io.Serializable;

public class Tag implements Serializable {

    private String name;
    private int counts;
    private int totalCounts;
//    private int actionStreamCount = -999999;
    private Integer actionStreamCount;

    public Tag() {
    }

    @Override
    public String toString() {
        return TagSerializer.toJsonObject(this);
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public int getCounts() {
        return counts;
    }

    public Tag setCounts(int counts) {
        this.counts = counts;
        return this;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public Tag setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
        return this;
    }

    public Integer getActionStreamCount() {
        return actionStreamCount;
    }

    public Tag setActionStreamCount(Integer actionStreamCount) {
        this.actionStreamCount = actionStreamCount;
        return this;
    }
}
