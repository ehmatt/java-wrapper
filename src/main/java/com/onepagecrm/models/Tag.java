package com.onepagecrm.models;

import com.onepagecrm.net.ApiResource;

import java.io.Serializable;

public class Tag extends ApiResource implements Serializable {

    private String id;
    private String name;
    private int counts;
    private int totalCounts;
    private int actionStreamCount;

    public Tag() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Tag setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return null;
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

    public int getActionStreamCount() {
        return actionStreamCount;
    }

    public Tag setActionStreamCount(int actionStreamCount) {
        this.actionStreamCount = actionStreamCount;
        return this;
    }
}