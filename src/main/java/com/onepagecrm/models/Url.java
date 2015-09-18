package com.onepagecrm.models;

import com.onepagecrm.models.serializer.UrlSerializer;

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

    public String toString() {
        return UrlSerializer.toJsonObject(this);
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
