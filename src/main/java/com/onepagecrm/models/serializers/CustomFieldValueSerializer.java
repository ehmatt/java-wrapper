package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.CustomFieldValue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

public class CustomFieldValueSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CustomFieldValueSerializer.class.getName());

    public static CustomFieldValue fromJsonObject(JSONObject customFieldObject) {
        CustomFieldValue customFieldValue = new CustomFieldValue();
        if (customFieldObject.has(VALUE_TAG)) {
            try {
                Object valueObject = customFieldObject.get(VALUE_TAG);
                if (valueObject instanceof Number) {
                    // Longs are of type Number in Java.
                    if (valueObject instanceof Long) {
                        // Convert this to a Long.
                        valueObject = ((Number) valueObject).longValue();
                    } else {
                        // Floating point numbers are of type Number in Java.
                        // Convert this to a Double.
                        valueObject = ((Number) valueObject).doubleValue();
                    }
                } else if (valueObject instanceof JSONArray) {
                    // Parse array of Strings.
                    valueObject = toArrayOfStrings((JSONArray) valueObject);
                }
                customFieldValue.setValue(valueObject);
            } catch (JSONException e) {
                LOG.severe("No value tag present");
                LOG.severe(e.toString());
            }
        }
        return customFieldValue;
    }

    public static String toJsonObject(CustomFieldValue customFieldValue, JSONObject valueObject) {
        if (customFieldValue != null) {
            if (customFieldValue.getValue() instanceof String) {
                addJsonStringValue((String) customFieldValue.getValue(), valueObject, VALUE_TAG);
            } else if (customFieldValue.getValue() instanceof Long) {
                addJsonLongValue((Long) customFieldValue.getValue(), valueObject, VALUE_TAG);
            } else if (customFieldValue.getValue() instanceof Double) {
                addJsonDoubleValue((Double) customFieldValue.getValue(), valueObject, VALUE_TAG);
            } else if (customFieldValue.getValue() instanceof String[]) {
                JSONArray valueArray = toJsonStringArray((String[]) customFieldValue.getValue());
                addJsonArray(valueArray, valueObject, VALUE_TAG);
            }
        }
        return valueObject.toString();
    }

    public static String toJsonObject(CustomFieldValue customFieldValue) {
        JSONObject valueObject = new JSONObject();
        if (customFieldValue != null) {
            if (customFieldValue.getValue() instanceof String) {
                addJsonStringValue((String) customFieldValue.getValue(), valueObject, VALUE_TAG);
            } else if (customFieldValue.getValue() instanceof Long) {
                addJsonLongValue((Long) customFieldValue.getValue(), valueObject, VALUE_TAG);
            } else if (customFieldValue.getValue() instanceof Float) {
                addJsonFloatValue((Float) customFieldValue.getValue(), valueObject, VALUE_TAG);
            } else if (customFieldValue.getValue() instanceof String[]) {
                JSONArray valueArray = toJsonStringArray((String[]) customFieldValue.getValue());
                addJsonArray(valueArray, valueObject, VALUE_TAG);
            }
        }
        return valueObject.toString();
    }
}
