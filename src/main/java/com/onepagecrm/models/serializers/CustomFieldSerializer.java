package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.CustomField;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 25/09/2017.
 */
@SuppressWarnings("WeakerAccess")
public class CustomFieldSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CustomFieldSerializer.class.getName());

    private static CustomField DEFAULT = new CustomField();

    public static List<CustomField> fromResponse(Response response, String cfType) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        final String customFieldsTag = getTagPlural(cfType);
        JSONArray customFieldsArray = dataObject.optJSONArray(customFieldsTag);
        return fromJsonArray(customFieldsArray, cfType);
    }

    // TODO: delete me
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

    public static CustomField fromJsonObject(JSONObject outerObject, String cfType) {
        if (outerObject == null) {
            return DEFAULT;
        }

        final String customFieldTag = getTagSingle(cfType);
        CustomField customField = new CustomField();

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

        return customField.setCfType(cfType)
                .setId(customFieldObject.optString(ID_TAG))
                .setName(customFieldObject.optString(NAME_TAG))
                .setPosition(customFieldObject.optInt(POSITION_TAG))
                .setType(customFieldObject.optString(TYPE_TAG));
    }

    public static List<CustomField> fromJsonArray(JSONArray customFieldArray, String cfType) {
        List<CustomField> customFields = new ArrayList<>();
        if (customFieldArray == null) return customFields;
        for (int i = 0; i < customFieldArray.length(); i++) {
            JSONObject outerObject = customFieldArray.optJSONObject(i);
            CustomField customField = fromJsonObject(outerObject, cfType);
            customFields.add(customField);
        }
        return customFields;
    }

    public static JSONObject toJsonObject(CustomField customField) {
        JSONObject customFieldObject = new JSONObject();
        if (customField == null) return customFieldObject;
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
        CustomFieldValueSerializer.addToJsonObject(customField.getValue(), customFieldObject);
        return customFieldObject;
    }

    public static String toJsonString(CustomField customField) {
        return toJsonObject(customField).toString();
    }

    public static JSONObject toJsonObjectFull(CustomField customField) {
        JSONObject customFieldObject = new JSONObject();
        if (customField == null) return customFieldObject;
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
        final String cfType = getTagSingle(customField.getCfType());
        final String defaultTag = getTagSingle(CustomField.CF_TYPE_CONTACT);
        final String tag = notNullOrEmpty(cfType) ? cfType : defaultTag;
        JSONObject outerObject = new JSONObject();
        addJsonObject(customFieldObject, outerObject, tag);
        CustomFieldValueSerializer.addToJsonObject(customField.getValue(), outerObject);
        return outerObject;
    }

    public static String toJsonStringFull(CustomField customField) {
        return toJsonObjectFull(customField).toString();
    }

    public static JSONArray toJsonArray(List<CustomField> customFields) {
        JSONArray customFieldsArray = new JSONArray();
        if (customFields == null) return customFieldsArray;
        for (CustomField customField : customFields) {
            customFieldsArray.put(toJsonObject(customField));
        }
        return customFieldsArray;
    }

    public static String toJsonString(List<CustomField> customFields) {
        return toJsonArray(customFields).toString();
    }

    public static JSONArray toJsonArrayFull(List<CustomField> customFields) {
        JSONArray customFieldsArray = new JSONArray();
        if (customFields == null) return customFieldsArray;
        for (CustomField customField : customFields) {
            customFieldsArray.put(toJsonObjectFull(customField));
        }
        return customFieldsArray;
    }

    public static String toJsonStringFull(List<CustomField> customFields) {
        return toJsonArrayFull(customFields).toString();
    }

    private static String getChoicesJsonArray(CustomField customField) {
        return toJsonStringArray(customField.getChoices()).toString();
    }

    public static String getTagSingle(String cfType) {
        if (!notNullOrEmpty(cfType)) return null;
        switch (cfType) {
            case CustomField.CF_TYPE_CONTACT:
                return BaseSerializer.CUSTOM_FIELD_TAG;
            case CustomField.CF_TYPE_COMPANY:
                return BaseSerializer.COMPANY_FIELD_TAG;
            case CustomField.CF_TYPE_DEAL:
                return BaseSerializer.DEAL_FIELD_TAG;
        }
        return "";
    }

    public static String getTagPlural(String cfType) {
        if (!notNullOrEmpty(cfType)) return null;
        switch (cfType) {
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
