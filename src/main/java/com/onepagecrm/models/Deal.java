package com.onepagecrm.models;

import com.onepagecrm.net.ApiResource;


public class Deal extends ApiResource {

    private String id;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Deal setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return null;
    }
}
