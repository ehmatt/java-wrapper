package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.Map;

public class StreamCount implements Serializable {

    private Map<String, TeamCount> users;
    private Integer all;

    public StreamCount() {

    }

    public String toString() {
        return "users=\'" + users + "\', all=\'" + all;
    }

    public Map<String, TeamCount> getUsers() {
        return users;
    }

    public StreamCount setUsers(Map<String, TeamCount> users) {
        this.users = users;
        return this;
    }

    public Integer getAll() {
        return all;
    }

    public StreamCount setAll(Integer all) {
        this.all = all;
        return this;
    }
}
