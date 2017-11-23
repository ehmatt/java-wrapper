package com.onepagecrm.models;

import com.onepagecrm.models.serializers.EmailSerializer;

import java.io.Serializable;

public class Email extends BaseResource implements Serializable {

    public static final String TYPE_WORK = "work";
    public static final String TYPE_HOME = "home";
    public static final String TYPE_OTHER = "other";

    private String type;
    private String value;

    public Email(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public Email() {

    }

    public String toString() {
        return EmailSerializer.toJsonString(this);
    }

    /**
     * Method to compare Email obj's to one another based off of their value attr.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Email) {
            Email toCompare = (Email) object;
            if (this.value != null && toCompare.value != null) {
                return this.value.equals(toCompare.value);
            }
        }
        return false;
    }

    public String getType() {
        return type;
    }

    public Email setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Email setValue(String value) {
        this.value = value;
        return this;
    }
}
