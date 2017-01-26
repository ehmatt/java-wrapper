package com.onepagecrm.models.serializers;

import com.onepagecrm.models.CustomField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

@SuppressWarnings("WeakerAccess")
public class CustomFieldSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CustomFieldSerializer.class.getName());

    public static List<CustomField> fromString(String responseBody, String cfType) {
        List<CustomField> customFields = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            int status = responseObject.getInt(STATUS_TAG);
            String message = responseObject.getString(MESSAGE_TAG);

            if (status == 0 && message.equalsIgnoreCase(OK_TAG)) {
                JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
                final String customFieldsTag = getTagPlural(cfType);
                JSONArray customFieldsArray = dataObject.getJSONArray(customFieldsTag);
                customFields = fromJsonArray(customFieldsArray, cfType);
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing CustomField's from response body");
            LOG.severe(e.toString());
        }
        return customFields;
    }

    public static List<CustomField> fromJsonArray(JSONArray customFieldArray, String cfType) {
        List<CustomField> customFields = new ArrayList<>();
        for (int i = 0; i < customFieldArray.length(); i++) {
            try {
                JSONObject outerObject = customFieldArray.getJSONObject(i);
                CustomField customField = fromJsonObject(outerObject, cfType);
                customFields.add(customField);
            } catch (JSONException e) {
                LOG.severe("Error parsing CustomField's from JsonArray");
                LOG.severe(e.toString());
            }
        }
        return customFields;
    }

    public static CustomField fromJsonObject(JSONObject outerObject, String cfType) {
        CustomField customField = new CustomField();

        try {
            if (outerObject.has(VALUE_TAG)) {
                customField.setValue(CustomFieldValueSerializer.fromJsonObject(outerObject));
            }
            final String customFieldTag = getTagSingle(cfType);
            JSONObject customFieldObject = outerObject.getJSONObject(customFieldTag);

            if (customFieldObject.has(CHOICES_TAG)) {
                JSONArray choicesArray = customFieldObject.getJSONArray(CHOICES_TAG);
                List<String> choices = toListOfStrings(choicesArray);
                if (!choices.isEmpty()) {
                    customField.setChoices(choices);
                }
            }

            if (customFieldObject.has(REMINDER_DAYS_TAG)) {
                if (!customFieldObject.isNull(REMINDER_DAYS_TAG)) {
                    int reminderDays = customFieldObject.getInt(REMINDER_DAYS_TAG);
                    customField.setReminderDays(reminderDays);
                } else {
                    // Does it make sense to set this to -1 when null returned??
                    customField.setReminderDays(-1);
                }
            }

            customField.setCfType(cfType)
                    .setId(customFieldObject.optString(ID_TAG))
                    .setName(customFieldObject.optString(NAME_TAG))
                    .setPosition(customFieldObject.optInt(POSITION_TAG))
                    .setType(customFieldObject.optString(TYPE_TAG));

        } catch (JSONException e) {
            LOG.severe("Error parsing CustomField from JsonObject");
            LOG.severe(e.toString());
        }
        return customField;
    }

    public static String toJsonObject(CustomField customField) {
        JSONObject object = new JSONObject();
        JSONObject customFieldObject = new JSONObject();
        addJsonStringValue(customField.getId(), customFieldObject, ID_TAG);

        try {
            JSONArray choicesArray = new JSONArray(getChoicesJsonArray(customField));
            // Adds empty array with key.
            customFieldObject.put(CHOICES_TAG, choicesArray);
            // Adds no array when it's empty.
            //addJsonArray(choicesArray, customFieldObject, CHOICES_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating JSONArray out of CustomField choices");
            LOG.severe(e.toString());
        }
        addJsonStringValue(customField.getName(), customFieldObject, NAME_TAG);
        addJsonIntegerValue(customField.getPosition(), customFieldObject, POSITION_TAG);
        addJsonStringValue(customField.getType(), customFieldObject, TYPE_TAG);
        addJsonIntegerValue(customField.getReminderDays(), customFieldObject, REMINDER_DAYS_TAG);
        final String lCfType = getTagSingle(customField.getCfType());
        final String lTag = notNullOrEmpty(lCfType) ? lCfType : CustomField.CF_TYPE_CONTACT;
        addJsonObject(customFieldObject, object, lTag);
        CustomFieldValueSerializer.toJsonObject(customField.getValue(), object);
        return object.toString();
    }

    public static String toJsonObjectNew(CustomField customField) {
        JSONObject customFieldObject = new JSONObject();
        addJsonStringValue(customField.getId(), customFieldObject, ID_TAG);

        try {
            JSONArray choicesArray = new JSONArray(getChoicesJsonArray(customField));
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
        CustomFieldValueSerializer.toJsonObject(customField.getValue(), customFieldObject);
        return customFieldObject.toString();
    }

    public static String toJsonArray(List<CustomField> customFields) {
        JSONArray customFieldsArray = new JSONArray();
        if (customFields != null && !customFields.isEmpty()) {
            for (int i = 0; i < customFields.size(); i++) {
                try {
                    customFieldsArray.put(new JSONObject(toJsonObject(customFields.get(i))));
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of CustomField");
                    LOG.severe(e.toString());
                }
            }
        }
        return customFieldsArray.toString();
    }

    private static String getChoicesJsonArray(CustomField customField) {
        return toJsonStringArray(customField.getChoices()).toString();
    }

    public static String getTagSingle(String cfType) {
        if (!notNullOrEmpty(cfType)) return null;
        switch (cfType) {
            case CustomField.CF_TYPE_COMPANY:
                return BaseSerializer.COMPANY_FIELD_TAG;
            case CustomField.CF_TYPE_CONTACT:
                return BaseSerializer.CUSTOM_FIELD_TAG;
        }
        return "";
    }

    public static String getTagPlural(String cfType) {
        if (!notNullOrEmpty(cfType)) return null;
        switch (cfType) {
            case CustomField.CF_TYPE_COMPANY:
                return BaseSerializer.COMPANY_FIELDS_TAG;
            case CustomField.CF_TYPE_CONTACT:
                return BaseSerializer.CUSTOM_FIELDS_TAG;
        }
        return "";
    }
}
