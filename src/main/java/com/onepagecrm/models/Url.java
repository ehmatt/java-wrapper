package com.onepagecrm.models;

import java.io.Serializable;

public class Url implements Serializable {

    private String type;
    private String value;

    public Url(String type, String value) {
        this.setType(type);
        this.setValue(value);
    }

    public Url() {
    }

    public String getType() {
        return type;
    }

    public Url setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Url setValue(String value) {
        this.value = value;
        return this;
    }
}
