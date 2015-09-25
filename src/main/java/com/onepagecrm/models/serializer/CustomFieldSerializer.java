package com.onepagecrm.models.serializer;

import com.onepagecrm.models.CustomField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomFieldSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CustomFieldSerializer.class.getName());

    public static List<CustomField> fromString(String responseBody) {
        List<CustomField> customFields = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            int status = responseObject.getInt(STATUS_TAG);
            String message = responseObject.getString(MESSAGE_TAG);

            if (status == 0 && message.equalsIgnoreCase(OK_TAG)) {
                JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
                JSONArray customFieldsArray = dataObject.getJSONArray(CUSTOM_FIELDS_TAG);
                customFields = fromJsonArray(customFieldsArray);
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing CustomField's from response body");
            LOG.severe(e.toString());
        }
        return customFields;
    }

    public static List<CustomField> fromJsonArray(JSONArray customFieldArray) {
        List<CustomField> customFields = new ArrayList<>();
        for (int i = 0; i < customFieldArray.length(); i++) {
            try {
                JSONObject outerObject = customFieldArray.getJSONObject(i);
                CustomField customField = fromJsonObject(outerObject);
                customFields.add(customField);
            } catch (JSONException e) {
                LOG.severe("Error parsing CustomField's from JsonArray");
                LOG.severe(e.toString());
            }
        }
        return customFields;
    }

    public static CustomField fromJsonObject(JSONObject outerObject) {
        CustomField customField = new CustomField();
        try {
            if (outerObject.has(VALUE_TAG)) {
                customField.setValue(outerObject.get(VALUE_TAG));
            }
            JSONObject customFieldObject = outerObject.getJSONObject(CUSTOM_FIELD_TAG);
            String id = customFieldObject.getString(ID_TAG);

            List<String> choices = new ArrayList<>();
            JSONArray choicesArray = customFieldObject.getJSONArray(CHOICES_TAG);
            for (int i = 0; i < choicesArray.length(); i++) {
                String choice = choicesArray.getString(i);
                choices.add(choice);
            }

            if (!choices.isEmpty()) {
                customField.setChoices(choices);
            }

            String name = customFieldObject.getString(NAME_TAG);
            int position = customFieldObject.getInt(POSITION_TAG);
            String type = customFieldObject.getString(TYPE_TAG);

            if (customFieldObject.has(REMINDER_DAYS_TAG)) {
                if (!customFieldObject.isNull(REMINDER_DAYS_TAG)) {
                    int reminderDays = customFieldObject.getInt(REMINDER_DAYS_TAG);
                    customField.setReminderDays(reminderDays);
                } else {
                    // TODO : review this.
                    // Does it make sense to set this to -1 when null returned??
                    customField.setReminderDays(-1);
                }
            }

            customField.setId(id)
                    .setName(name)
                    .setPosition(position)
                    .setType(type);

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
//            addJsonArray(choicesArray, customFieldObject, CHOICES_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating JSONArray out of CustomField choices");
            LOG.severe(e.toString());
        }
        addJsonStringValue(customField.getName(), customFieldObject, NAME_TAG);
        addJsonIntValue(customField.getPosition(), customFieldObject, POSITION_TAG);
        addJsonStringValue(customField.getType(), customFieldObject, TYPE_TAG);
        addJsonIntValue(customField.getReminderDays(), customFieldObject, REMINDER_DAYS_TAG);
        addJsonObject(customFieldObject, object, CUSTOM_FIELD_TAG);
        addJsonStringValue(customField.getValue(), object, VALUE_TAG);
        return object.toString();
    }

    private static String getChoicesJsonArray(CustomField customField) {
        JSONArray choicesArray = new JSONArray();
        if (customField.getChoices() != null) {
            for (int i = 0; i < customField.getChoices().size(); i++) {
                choicesArray.put(customField.getChoices().get(i));
            }
        }
        return choicesArray.toString();
    }
}
