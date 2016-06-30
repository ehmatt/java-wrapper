package com.onepagecrm.models.internal;

import java.io.Serializable;

public class TeamCount implements Serializable {

    private String userId;
    private Integer count;

    @Override
    public String toString() {
        return "TeamCount{" +
                "userId='" + userId + '\'' +
                ", count=" + count +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public TeamCount setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public TeamCount setCount(Integer count) {
        this.count = count;
        return this;
    }
}