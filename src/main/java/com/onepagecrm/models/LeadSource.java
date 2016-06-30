package com.onepagecrm.models;

import com.onepagecrm.models.internal.TeamCount;
import com.onepagecrm.models.serializers.LeadSourceSerializer;
import com.onepagecrm.net.ApiResource;

import java.io.Serializable;
import java.util.List;

public class LeadSource extends ApiResource implements Serializable {

    private String id;
    private String text;
    private Integer count;
    private Integer totalCount;
    private Integer actionStreamCount;
    private List<TeamCount> teamCounts;

    public LeadSource() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public LeadSource setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return LeadSourceSerializer.toJsonObject(this);
    }

    public String getText() {
        return text;
    }

    public LeadSource setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public LeadSource setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public LeadSource setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public Integer getActionStreamCount() {
        return actionStreamCount;
    }

    public LeadSource setActionStreamCount(Integer actionStreamCount) {
        this.actionStreamCount = actionStreamCount;
        return this;
    }

    public List<TeamCount> getTeamCounts() {
        return teamCounts;
    }

    public LeadSource setTeamCounts(List<TeamCount> teamCounts) {
        this.teamCounts = teamCounts;
        return this;
    }
}
