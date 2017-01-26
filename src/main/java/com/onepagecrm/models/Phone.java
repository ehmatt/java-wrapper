package com.onepagecrm.models;

import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.models.serializers.PhoneSerializer;

import java.io.Serializable;

public class Phone extends BaseResource implements Serializable {

    static final long serialVersionUID = 3927723378236473122L;

    public static final String TYPE_WORK = "work";
    public static final String TYPE_MOBILE = "mobile";
    public static final String TYPE_HOME = "home";
    public static final String TYPE_DIRECT = "direct";
    public static final String TYPE_FAX = "fax";
    public static final String TYPE_SKYPE = "skype";
    public static final String TYPE_COMPANY = "company";
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

    /**
     * Method to compare Phone obj's to one another based off of their value attr.
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Phone) {
            Phone toCompare = (Phone) object;
            if (this.value != null && toCompare.value != null) {
                return this.value.equals(toCompare.value);
            }
        }
        return false;
    }

    public String getPhoneString() {
        if (type.isEmpty()) return value;
        else return String.format("%s (%s)", value, Utilities.capitalize(type));
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
