package com.onepagecrm.models.internal;

public class TeamCounts {

    private String userId;
    private int counts;

    public TeamCounts() {

    }

    public TeamCounts(String userId, int counts) {
        this.userId = userId;
        this.counts = counts;
    }

    public String toString() {
        return "userId=\'" + userId + "\', counts=\'" + counts;
    }

    public String getUserId() {
        return userId;
    }

    public TeamCounts setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public int getCounts() {
        return counts;
    }

    public TeamCounts setCounts(int counts) {
        this.counts = counts;
        return this;
    }
}