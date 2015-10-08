package com.onepagecrm.models;

import com.onepagecrm.models.serializer.TagSerializer;

import java.io.Serializable;

public class Tag implements Serializable {

    private String name;
    private Integer counts;
    private Integer totalCounts;
    private Integer actionStreamCount;

    public Tag() {
    }

    public Tag(Tag tag) {
        this.name = tag.getName();
        this.counts = tag.getCounts();
        this.totalCounts = tag.getTotalCounts();
        this.actionStreamCount = tag.getActionStreamCount();
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

    public Integer getCounts() {
        return counts;
    }

    public Tag setCounts(Integer counts) {
        this.counts = counts;
        return this;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public Tag setTotalCounts(Integer totalCounts) {
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
