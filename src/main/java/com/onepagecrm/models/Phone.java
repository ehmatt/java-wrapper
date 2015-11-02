package com.onepagecrm.models;

import com.onepagecrm.models.serializer.PhoneSerializer;

import java.io.Serializable;

public class Phone implements Serializable {

    static final long serialVersionUID = 3927723378236473122L;

    public static final String TYPE_WORK = "work";
    public static final String TYPE_MOBILE = "mobile";
    public static final String TYPE_HOME = "home";
    public static final String TYPE_DIRECT = "direct";
    public static final String TYPE_FAX = "fax";
    public static final String TYPE_SKYPE = "skype";
    public static final String TYPE_OTHER = "other";

    private String type;
    private String value;

    public Phone(String type, String value) {
        this.setType(type);
        this.setValue(value);
    }

    public Phone() {
    }

    public String toString() {
        return PhoneSerializer.toJsonObject(this);
    }

    public String getType() {
        return type;
    }

    public Phone setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Phone setValue(String value) {
        this.value = value;
        return this;
    }
}
