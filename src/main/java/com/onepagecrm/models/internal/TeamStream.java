package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.List;

public class TeamStream implements Serializable {

    private List<TeamCounts> users;
    private int all;

    public TeamStream() {

    }

    public TeamStream(List<TeamCounts> users, int all) {
        this.users = users;
        this.all = all;
    }

    public String toString() {
        return "users=\'" + users + "\', all=\'" + all;
    }

    public List<TeamCounts> getUsers() {
        return users;
    }

    public TeamStream setUsers(List<TeamCounts> users) {
        this.users = users;
        return this;
    }

    public int getAll() {
        return all;
    }

    public TeamStream setAll(int all) {
        this.all = all;
        return this;
    }
}
