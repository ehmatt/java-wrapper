package com.onepagecrm.models.serializers;

import com.onepagecrm.models.CustomField;
import com.onepagecrm.models.serializers.impl.SerializableAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.logging.Logger;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 20/12/2017.
 */
public class CustomFieldBaseSerializer extends SerializableAPI<CustomField> {

    private static final Logger LOG = Logger.getLogger(CustomFieldBaseSerializer.class.getName());

    private String type;

    CustomFieldBaseSerializer(String type) {
        this.type = type;
    }

    @Override
    protected CustomField singleResource() {
        return new CustomField().setCfType(type);
    }

    @Override
    protected String singleTag() {
        return getTagSingle(type);
    }

    @Override
    protected String multipleTag() {
        return getTagPlural(type);
    }

    @Override
    protected CustomField fromJsonObjectImpl(JSONObject outerObject) {
        CustomField customField = new CustomField();
        final String customFieldTag = getTagSingle(type);
        JSONObject customFieldObject = outerObject.optJSONObject(customFieldTag);

        if (outerObject.has(VALUE_TAG)) {
            customField.setValue(CustomFieldValueSerializer.fromJsonObject(outerObject));
        }

        if (customFieldObject.has(CHOICES_TAG)) {
            JSONArray choicesArray = customFieldObject.optJSONArray(CHOICES_TAG);
            List<String> choices = toListOfStrings(choicesArray);
            customField.setChoices(choices);
        }

        if (customFieldObject.has(REMINDER_DAYS_TAG)) {
            if (!customFieldObject.isNull(REMINDER_DAYS_TAG)) {
                int reminderDays = customFieldObject.optInt(REMINDER_DAYS_TAG);
                customField.setReminderDays(reminderDays);
            } else {
                // Does it make sense to set this to -1 when null returned??
                customField.setReminderDays(-1);
            }
        }

        return customField.setCfType(type)
                .setId(customFieldObject.optString(ID_TAG))
                .setName(customFieldObject.optString(NAME_TAG))
                .setPosition(customFieldObject.optInt(POSITION_TAG))
                .setType(customFieldObject.optString(TYPE_TAG));
    }

    @Override
    protected JSONObject toJsonObjectImpl(CustomField customField) {
        JSONObject customFieldObject = new JSONObject();
        addJsonStringValue(customField.getId(), customFieldObject, ID_TAG);
        try {
            JSONArray choicesArray = choicesJsonArray(customField);
            // Adds empty array with key.
            customFieldObject.put(CHOICES_TAG, choicesArray);
        } catch (JSONException e) {
            LOG.severe("Error creating JSONArray out of CustomField choices");
            LOG.severe(e.toString());
        }
        addJsonStringValue(customField.getName(), customFieldObject, NAME_TAG);
        addJsonIntegerValue(customField.getPosition(), customFieldObject, POSITION_TAG);
        addJsonStringValue(customField.getType(), customFieldObject, TYPE_TAG);
        addJsonIntegerValue(customField.getReminderDays(), customFieldObject, REMINDER_DAYS_TAG);
        CustomFieldValueSerializer.addToJsonObject(customField.getValue(), customFieldObject);
        return customFieldObject;
    }

    private static JSONArray choicesJsonArray(CustomField customField) {
        return toJsonStringArray(customField.getChoices());
    }

    private static String getTagSingle(String type) {
        if (!notNullOrEmpty(type)) return null;
        switch (type) {
            case CustomField.CF_TYPE_CONTACT:
                return BaseSerializer.CUSTOM_FIELD_TAG;
            case CustomField.CF_TYPE_COMPANY:
                return BaseSerializer.COMPANY_FIELD_TAG;
            case CustomField.CF_TYPE_DEAL:
                return BaseSerializer.DEAL_FIELD_TAG;
        }
        return "";
    }

    private static String getTagPlural(String type) {
        if (!notNullOrEmpty(type)) return null;
        switch (type) {
            case CustomField.CF_TYPE_CONTACT:
                return BaseSerializer.CUSTOM_FIELDS_TAG;
            case CustomField.CF_TYPE_COMPANY:
                return BaseSerializer.COMPANY_FIELDS_TAG;
            case CustomField.CF_TYPE_DEAL:
                return BaseSerializer.DEAL_FIELDS_TAG;
        }
        return "";
    }
}
