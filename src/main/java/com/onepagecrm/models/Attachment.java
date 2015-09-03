package com.onepagecrm.models;

import com.onepagecrm.net.ApiResource;

import java.io.Serializable;

public class Attachment extends ApiResource implements Serializable {

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public Attachment setId(String id) {
        super.setId(id);
        return this;
    }

    @Override
    public String toString() {
        return null;
    }
}
