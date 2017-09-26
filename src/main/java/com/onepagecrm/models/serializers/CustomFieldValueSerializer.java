package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.CustomFieldValue;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 26/09/2017.
 */
@SuppressWarnings("WeakerAccess")
public class CustomFieldValueSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CustomFieldValueSerializer.class.getName());

    private static CustomFieldValue DEFAULT = new CustomFieldValue();

    public static CustomFieldValue fromJsonObject(JSONObject customFieldObject) {
        if (customFieldObject == null) {
            return DEFAULT;
        }
        CustomFieldValue customFieldValue = new CustomFieldValue();
        Object valueObject = customFieldObject.opt(VALUE_TAG);
        if (valueObject != null) {
            if (valueObject instanceof Number) {
                // Longs are of type Number in Java.
                if (valueObject instanceof Long) {
                    // Convert this to a Long.
                    valueObject = ((Number) valueObject).longValue();
                } else {
                    // Floating point numbers are of type Number in Java.
                    // Convert this to a BigDecimal.
                    valueObject = new BigDecimal(valueObject.toString());
                }
            } else if (valueObject instanceof JSONArray) {
                // Parse array of Strings.
                valueObject = toArrayOfStrings((JSONArray) valueObject);
            }
        }
        customFieldValue.setValue(valueObject);
        return customFieldValue;
    }

    public static JSONObject toJsonObject(CustomFieldValue customFieldValue) {
        return addToJsonObject(customFieldValue, null);
    }

    public static JSONObject addToJsonObject(CustomFieldValue customFieldValue, JSONObject valueObject) {
        JSONObject customFieldValueObject = valueObject != null ? valueObject : new JSONObject();
        if (customFieldValue == null) return customFieldValueObject;
        if (customFieldValue.getValue() instanceof String) {
            addJsonStringValue((String) customFieldValue.getValue(), customFieldValueObject, VALUE_TAG);
        } else if (customFieldValue.getValue() instanceof Long) {
            addJsonLongValue((Long) customFieldValue.getValue(), customFieldValueObject, VALUE_TAG);
        } else if (customFieldValue.getValue() instanceof BigDecimal) {
            addJsonBigDecimalValue((BigDecimal) customFieldValue.getValue(), customFieldValueObject, VALUE_TAG);
        } else if (customFieldValue.getValue() instanceof String[]) {
            JSONArray valueArray = toJsonStringArray((String[]) customFieldValue.getValue());
            addJsonArray(valueArray, customFieldValueObject, VALUE_TAG);
        }
        return customFieldValueObject;
    }

    public static String toJsonString(CustomFieldValue customFieldValue) {
        return toJsonObject(customFieldValue).toString();
    }

    public static String toJsonString(CustomFieldValue customFieldValue, JSONObject valueObject) {
        return addToJsonObject(customFieldValue, valueObject).toString();
    }
}
