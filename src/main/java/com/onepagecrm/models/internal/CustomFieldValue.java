package com.onepagecrm.models.internal;

import com.onepagecrm.models.serializers.BaseSerializer;
import com.onepagecrm.models.serializers.CustomFieldValueSerializer;

import java.io.Serializable;

public class CustomFieldValue implements Serializable {

    private String stringValue;
    private Integer integerValue;
    private Double doubleValue;
    private String[] stringArrayValue;

    public Serializable getValue() {
        if (stringValue != null) {
            return this.stringValue;
        } else if (integerValue != null) {
            return this.integerValue;
        } else if (doubleValue != null) {
            return this.doubleValue;
        } else if (stringArrayValue != null) {
            return this.stringArrayValue;
        }
        return null;
    }

    public String getStringValue() {
        String retString = "";
        if (stringValue != null) {
            retString = this.stringValue;
        } else if (integerValue != null) {
            retString = String.valueOf(this.integerValue);
        } else if (doubleValue != null) {
            retString = String.valueOf(this.doubleValue);
        } else if (stringArrayValue != null) {
            retString = BaseSerializer.toCommaSeparatedString(this.stringArrayValue);
        }
        return retString;
    }

    public Serializable setValue(Object object) {
        if (object instanceof String) {
            this.setValue((String) object);
        }
        if (object instanceof Integer) {
            this.setValue((Integer) object);
        }
        if (object instanceof Double) {
            this.setValue((Double) object);
        }
        if (object instanceof String[]) {
            this.setValue((String[]) object);
        }
        return this;
    }

    @Override
    public String toString() {
        return CustomFieldValueSerializer.toJsonObject(this);
    }

    public Serializable setValue(String stringValue) {
        this.stringValue = stringValue;
        this.integerValue = null;
        this.doubleValue = null;
        this.stringArrayValue = null;
        return getValue();
    }

    public Serializable setValue(Integer integerValue) {
        this.stringValue = null;
        this.integerValue = integerValue;
        this.doubleValue = null;
        this.stringArrayValue = null;
        return getValue();
    }

    public Serializable setValue(Double doubleValue) {
        this.stringValue = null;
        this.integerValue = null;
        this.doubleValue = doubleValue;
        this.stringArrayValue = null;
        return getValue();
    }

    public Serializable setValue(String[] stringArrayValue) {
        this.stringValue = null;
        this.integerValue = null;
        this.doubleValue = null;
        this.stringArrayValue = stringArrayValue;
        return getValue();
    }
}
