package com.onepagecrm.models;

import com.onepagecrm.models.internal.TeamCounts;
import com.onepagecrm.net.ApiResource;

import java.io.Serializable;
import java.util.List;

public class LeadSource extends ApiResource implements Serializable {

    private String id;
    private String text;
    private int counts;
    private int totalCounts;
    private List<TeamCounts> teamCounts;

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
        return "LeadSource{" +
                "id=" + id +
                ", text=\'" + text + "\'" +
                ", counts=\'" + counts + "\'" +
                ", totalCounts=\'" + totalCounts + "\'" +
                ", teamCounts=\'" + teamCounts + "\'" +
                '}';
    }

    public String getText() {
        return text;
    }

    public LeadSource setText(String text) {
        this.text = text;
        return this;
    }

    public int getCounts() {
        return counts;
    }

    public LeadSource setCounts(int counts) {
        this.counts = counts;
        return this;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public LeadSource setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
        return this;
    }

    public List<TeamCounts> getTeamCounts() {
        return teamCounts;
    }

    public LeadSource setTeamCounts(List<TeamCounts> teamCounts) {
        this.teamCounts = teamCounts;
        return this;
    }
}
