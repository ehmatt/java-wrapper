package com.onepagecrm.models;

import com.onepagecrm.models.internal.TeamCount;
import com.onepagecrm.models.serializers.StatusSerializer;
import com.onepagecrm.net.ApiResource;

import java.io.Serializable;
import java.util.List;

public class Status extends ApiResource implements Serializable {

    private String id;
    private String color;
    private String status;
    private String text;
    private String description;
    private Integer count;
    private Integer totalCount;
    private Integer actionStreamCount;
    private List<TeamCount> teamCounts;

    public Status() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Status setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return StatusSerializer.toJsonObject(this);
    }

    public String getColor() {
        return color;
    }

    public Status setColor(String color) {
        this.color = color;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Status setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getText() {
        return text;
    }

    public Status setText(String text) {
        this.text = text;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Status setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Status setCount(Integer count) {
        this.count = count;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Status setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public Integer getActionStreamCount() {
        return actionStreamCount;
    }

    public Status setActionStreamCount(Integer actionStreamCount) {
        this.actionStreamCount = actionStreamCount;
        return this;
    }

    public List<TeamCount> getTeamCounts() {
        return teamCounts;
    }

    public Status setTeamCounts(List<TeamCount> teamCounts) {
        this.teamCounts = teamCounts;
        return this;
    }
}