package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Logger;

public class BaseSerializer {

    private static final Logger LOG = Logger.getLogger(BaseSerializer.class.getName());

    // LOGIN TAGS
    public static final String LOGIN_TAG = "login";
    public static final String PASSWORD_TAG = "password";
    protected static final String DATA_TAG = "data";
    protected static final String USER_ID_TAG = "user_id";
    protected static final String AUTH_KEY_TAG = "auth_key";
    protected static final String ACCOUNT_TYPE_TAG = "account_type";

    // LOGIN RESPONSE / USER TAGS
    protected static final String USER_TAG = "user";
    protected static final String FIRST_NAME_TAG = "first_name";
    protected static final String LAST_NAME_TAG = "last_name";
    protected static final String EMAIL_TAG = "email";
    protected static final String COMPANY_NAME_TAG = "company_name";
    protected static final String PHOTO_URL_TAG = "photo_url";
    protected static final String BCC_EMAIL_TAG = "bcc_email";

    // CONTACT TAGS
    protected static final String CONTACTS_TAG = "contacts";
    protected static final String CONTACT_TAG = "contact";
    protected static final String ID_TAG = "id";
    protected static final String COMPANY_ID_TAG = "company_id";
    protected static final String EMAILS_TAG = "emails";
    protected static final String JOB_TITLE_TAG = "job_title";
    protected static final String LEAD_SOURCE_ID_TAG = "lead_source_id";
    protected static final String OWNER_ID_TAG = "owner_id";
    protected static final String PHONES_TAG = "phones";
    protected static final String SALES_CLOSED_FOR_TAG = "sales_closed_for";
    protected static final String STARRED_TAG = "starred";
    protected static final String STATUS_ID_TAG = "status_id";
    protected static final String URLS_TAG = "urls";
    protected static final String BACKGROUND_TAG = "background";
    protected static final String ADDRESS_LIST_TAG = "address_list";

    // ADDRESS LIST TAGS
    protected static final String ADDRESS_TAG = "address";
    protected static final String CITY_TAG = "city";
    protected static final String STATE_TAG = "state";
    protected static final String ZIP_CODE_TAG = "zip_code";
    protected static final String COUNTRY_CODE_TAG = "country_code";

    // ACTION TAGS
    protected static final String NEXT_ACTIONS_TAG = "next_actions";
    protected static final String NEXT_ACTION_TAG = "next_action";
    protected static final String CONTACT_ID_TAG = "contact_id";
    protected static final String ASSIGNEE_ID_TAG = "assignee_id";
    protected static final String DATE_TAG = "date";

    // CALL TAGS
    public static final String CALL_TAG = "call";
    public static final String CALLS_TAG = "calls";
    public static final String CALL_RESULTS_TAG = "call_results";
    public static final String CALL_RESULT_TAG = "call_result";
    public static final String CALL_TIME_INT_TAG = "call_time_int";
    public static final String TEXT_TAG = "text";
    public static final String VIA_TAG = "via";
    public static final String AUTHOR_TAG = "author";
    public static final String ATTACHMENTS_TAG = "attachment";

    // 201 RESPONSE TAGS
    protected static final String STATUS_TAG = "status";
    protected static final String STATUSES_TAG = "statuses";
    protected static final String MESSAGE_TAG = "message";
    protected static final String TIMESTAMP_TAG = "timestamp";
    protected static final String CREATED_TAG = "Created";

    // GENERIC TAGS
    protected static final String TYPE_TAG = "type";
    protected static final String VALUE_TAG = "value";
    protected static final String CREATED_AT_TAG = "created_at";
    protected static final String MODIFIED_AT_TAG = "modified_at";
    protected static final String OK_TAG = "OK";

    // CUSTOM FIELDS TAGS
    protected static final String CUSTOM_FIELDS_TAG = "custom_fields";
    protected static final String CUSTOM_FIELD_TAG = "custom_field";
    protected static final String CHOICES_TAG = "choices";
    protected static final String NAME_TAG = "name";
    protected static final String POSITION_TAG = "position";
    protected static final String REMINDER_DAYS_TAG = "reminder_days";

    // TAGS TAGS
    protected static final String TAGS_TAG = "tags";
    protected static final String SYSTEM_TAGS_TAG = "system_tags";
    protected static final String COUNTS_TAG = "counts";
    protected static final String TOTAL_COUNT_TAG = "total_count";
    protected static final String ACTION_STREAM_COUNT_TAG = "action_stream_count";

    // ERROR TAGS
    protected static final String ERROR_NAME_TAG = "error_name";
    protected static final String ERROR_MESSAGE_TAG = "error_message";
    protected static final String ERRORS_TAG = "errors";

    // TEAM TAGS
    protected static final String TEAM_TAG = "team";
    protected static final String ACCOUNT_RIGHTS_TAG = "account_rights";

    // STATUS TAGS
    protected static final String TEAM_COUNTS_TAG = "team_counts";
    protected static final String COLOR_TAG = "color";
    protected static final String DESCRIPTION_TAG = "description";

    // LEAD SOURCES TAGS
    protected static final String LEAD_SOURCES_ID_TAG = "lead_sources";

    /**
     * Method used to parse the base/start of response.
     *
     * @param responseBody
     * @return
     * @throws OnePageException
     */
    public static Object fromString(String responseBody) throws OnePageException {
        String dataString = "";
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            int status = responseObject.getInt(STATUS_TAG);
            String message = responseObject.getString(MESSAGE_TAG);

            // OK response
            if (status == 0 && message.equalsIgnoreCase(OK_TAG)) {
                JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
                dataString = dataObject.toString();
            }

            // 201 response
            else if (status == 0 && message.equalsIgnoreCase(CREATED_TAG)) {
                JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
                dataString = dataObject.toString();
            }

            // Error
            else {
                throw ErrorSerializer.fromString(responseBody);
            }

        } catch (JSONException e) {
            LOG.severe("Error parsing response body");
            LOG.severe(e.toString());
        }
        return dataString;
    }

    /**
     * Encode request parameters.
     *
     * @param params
     * @return
     */
    public static String encodeParams(Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            String encodedString = "";
            int i = 0;
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (i > 0) {
                    encodedString += "&";
                }
                try {
                    encodedString += String.format("%s=%s",
                            URLEncoder.encode(param.getKey(), "UTF-8"),
                            URLEncoder.encode(param.getValue(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    LOG.severe("Error encoding url params : " + params.toString());
                    LOG.severe(e.toString());
                } finally {
                    i++;
                }
            }
            return encodedString;
        }
        return "";
    }

    /**
     * Adds a value to a JSONObject with the specified key.
     *
     * @param value
     * @param object
     * @param key
     */
    public static void addJsonStringValue(String value, JSONObject object, String key) {
        if ((value != null) && (!value.equals(""))) {
            try {
                object.put(key, value);
            } catch (JSONException e) {
                LOG.severe("Error serializing string value : " + value);
                LOG.severe(e.toString());
            }
        }
    }

    /**
     * Adds an int value to a JSONObject with the specified key.
     *
     * @param value
     * @param object
     * @param key
     */
    public static void addJsonIntValue(int value, JSONObject object, String key) {
        try {
            object.put(key, value);
        } catch (JSONException e) {
            LOG.severe("Error serializing integer value : " + value);
            LOG.severe(e.toString());
        }
    }

    /**
     * Adds an integer value to a JSONObject with the specified key.
     *
     * @param value
     * @param object
     * @param key
     */
    public static void addJsonIntegerValue(Integer value, JSONObject object, String key) {
        if (value != null) {
            try {
                object.put(key, value);
            } catch (JSONException e) {
                LOG.severe("Error serializing integer value : " + value);
                LOG.severe(e.toString());
            }
        }
    }

    /**
     * Adds a long value to a JSONObject with the specified key.
     *
     * @param value
     * @param object
     * @param key
     */
    public static void addJsonLongValue(long value, JSONObject object, String key) {
        try {
            object.put(key, value);
        } catch (JSONException e) {
            LOG.severe("Error serializing integer value : " + value);
            LOG.severe(e.toString());
        }
    }

    /**
     * Adds a boolean value to a JSONObject with the specified key.
     *
     * @param value
     * @param object
     * @param key
     */
    public static void addJsonBooleanValue(boolean value, JSONObject object, String key) {
        try {
            object.put(key, value);
        } catch (JSONException e) {
            LOG.severe("Error serializing boolean value : " + value);
            LOG.severe(e.toString());
        }
    }

    /**
     * Adds a nested JSONObject with the specified key if the object has some info (keys > 0).
     *
     * @param input
     * @param object
     * @param key
     */
    public static void addJsonObject(JSONObject input, JSONObject object, String key) {
        try {
            if (input.length() > 0)
                object.put(key, input);
        } catch (JSONException e) {
            LOG.severe("Error serializing JSON object : " + input);
            LOG.severe(e.toString());
        }
    }

    /**
     * Adds a nested JSONObject with the specified key if the object has some info (keys > 0).
     *
     * @param input
     * @param object
     */
    public static void addJsonObject(JSONObject input, JSONArray object) {
        if (input.length() > 0)
            object.put(object);
    }

    /**
     * Adds a nested JSONArray with the specified key if the array has some info (keys > 0).
     *
     * @param input
     * @param object
     * @param key
     */
    public static void addJsonArray(JSONArray input, JSONObject object, String key) {
        try {
            if (input.length() > 0) {
                object.put(key, input);
            }
        } catch (JSONException e) {
            LOG.severe("Error serializing JSON array : " + input);
            LOG.severe(e.toString());
        }
    }
}
