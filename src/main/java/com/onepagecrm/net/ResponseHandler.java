package com.onepagecrm.net;

import com.onepagecrm.models.CallResult;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.Phone;
import com.onepagecrm.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class ResponseHandler {

    private static final String TAG = ResponseHandler.class.getSimpleName();

    // LOGIN TAGS
    private static final String DATA_TAG = "data";
    private static final String USER_ID_TAG = "user_id";
    private static final String AUTH_KEY_TAG = "auth_key";
    private static final String ACCOUNT_TYPE_TAG = "account_type";

    // LOGIN RESPONSE / USER TAGS
    private static final String USER_TAG = "user";
    private static final String FIRST_NAME_TAG = "first_name";
    private static final String LAST_NAME_TAG = "last_name";
    private static final String EMAIL_TAG = "email";
    private static final String COMPANY_NAME_TAG = "company_name";
    private static final String PHOTO_URL_TAG = "photo_url";
    private static final String BCC_EMAIL_TAG = "bcc_email";

    // CONTACT TAGS
    private static final String CONTACTS_TAG = "contacts";
    private static final String CONTACT_TAG = "contact";
    private static final String ID_TAG = "id";
    private static final String OWNER_ID_TAG = "owner_id";
    private static final String PHONES_TAG = "phones";
    private static final String TYPE_TAG = "type";
    private static final String VALUE_TAG = "value";
    private static final String STARRED_TAG = "starred";

    // 201 RESPONSE TAGS
    private static final String STATUS_TAG = "status";
    private static final String MESSAGE_TAG = "message";


    /**
     * Parse response from Login request to construct User object.
     *
     * @param responseBody
     * @return
     */
    public User parseLoginResponse(String responseBody) {

        User user = new User();
//        Log.d(TAG, "Parsing login details to User object");

        try {
            JSONObject responseObject = new JSONObject(responseBody);
//            Log.d(TAG, "responseObject=" + responseObject);

            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
//            Log.d(TAG, "dataObject=" + dataObject);

            String userId = dataObject.getString(USER_ID_TAG);
            String authKey = dataObject.getString(AUTH_KEY_TAG);
            String accountType = dataObject.getString(ACCOUNT_TYPE_TAG);

            // -----------------------------------------------------------------
            // TODO : Need to parse Call Results here
            // -----------------------------------------------------------------
            ArrayList<CallResult> callResults = new ArrayList<>();
            int index = 0;

            try {
                JSONObject callResultsObject = dataObject.getJSONObject("call_results");
//                Log.d(TAG, "callResultsObject=" + callResultsObject);

                Iterator<String> iterator = callResultsObject.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    try {
                        Object value = callResultsObject.get(key);
                        callResults.add(new CallResult(index, key, value.toString()));
                    } catch (JSONException e) {
//                        Log.e(TAG, "Failed to parse all values in call_results object");
                    }
                    index++;
                }
            } catch (JSONException e) {
//                Log.e(TAG, "No call_results JSON object in response", e);

                // TODO : remove this fabricated call results list
                callResults.add(new CallResult(0, "interested", "Interested"));
                callResults.add(new CallResult(1, "not_interested", "Not interested"));
                callResults.add(new CallResult(2, "left_message", "Left message"));
                callResults.add(new CallResult(3, "no_answer", "No answer"));
                callResults.add(new CallResult(4, "other", "Other"));
            }
            // -----------------------------------------------------------------

            JSONObject outsideUserObject = dataObject.getJSONObject(USER_TAG);
//            Log.d(TAG, "outsideUserObject=" + outsideUserObject);

            JSONObject userObject = outsideUserObject.getJSONObject(USER_TAG);
//            Log.d(TAG, "userObject=" + userObject);

            String firstName = userObject.getString(FIRST_NAME_TAG);
            String lastName = userObject.getString(LAST_NAME_TAG);
            String email = userObject.getString(EMAIL_TAG);
            String companyName = userObject.getString(COMPANY_NAME_TAG);
            String photoUrl = userObject.getString(PHOTO_URL_TAG);
            String bccEmail = userObject.getString(BCC_EMAIL_TAG);

            user = new User(userId, authKey, accountType, firstName, lastName, email,
                    companyName, photoUrl, bccEmail, callResults);
//            Log.d(TAG, "Parsed login details to User object successfully");

        } catch (JSONException e) {
//            Log.e(TAG, "Failed to parse login details to User object successfully", e);
        }

        return user;
    }

    /**
     * Parse response from mContacts/action_stream request to construct Contact object(s).
     *
     * @param responseBody
     * @return
     */
    public ArrayList<Contact> parseGetContactsResponse(String responseBody) {

        ArrayList<Contact> contacts = new ArrayList<>();
//        Log.d(TAG, "Parsing contact details");

        try {
            JSONObject responseObject = new JSONObject(responseBody);
//            Log.d(TAG, "responseObject=" + responseObject);

            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
//            Log.d(TAG, "dataObject=" + dataObject);

            JSONArray contactsArray = dataObject.getJSONArray(CONTACTS_TAG);
//            Log.d(TAG, "contactsArray=" + contactsArray);

            for (int i = 0; i < contactsArray.length(); i++) {
                JSONObject contactObject = contactsArray.getJSONObject(i);
//                Log.d(TAG, "contactObject=" + contactObject);

                JSONObject innerContactObject = contactObject.getJSONObject(CONTACT_TAG);
//                Log.d(TAG, "innerContactObject=" + innerContactObject);

                String id = innerContactObject.getString(ID_TAG);
                String owner_id = innerContactObject.getString(OWNER_ID_TAG);
                String firstName = innerContactObject.getString(FIRST_NAME_TAG);
                String lastName = innerContactObject.getString(LAST_NAME_TAG);

                JSONArray phonesArray = innerContactObject.getJSONArray(PHONES_TAG);
//                Log.d(TAG, "phonesArray=" + phonesArray);

                ArrayList<Phone> phones = new ArrayList<>();

                for (int j = 0; j < phonesArray.length(); j++) {
                    JSONObject phoneObject = phonesArray.getJSONObject(j);
//                    Log.d(TAG, "phoneObject=" + phoneObject);

                    String type = phoneObject.getString(TYPE_TAG);
                    String value = phoneObject.getString(VALUE_TAG);

                    Phone number = new Phone(type, value);

                    phones.add(number);
//                    Log.d(TAG, "phone: " + number.getType() + "=" + number.getNumber());
                }

                String companyName = innerContactObject.getString(COMPANY_NAME_TAG);
//                Log.d(TAG, "companyName=" + companyName);

                // TODO : remove this try catch block when API bug [] fixed.
                try {
                    boolean starred = innerContactObject.getBoolean(STARRED_TAG);
//                    Log.d(TAG, "starred=" + starred);
                    if (starred) {
                        Contact contact = new Contact(id, owner_id, firstName, lastName, phones, companyName);
                        contacts.add(contact);
                    }
//                    Log.d(TAG, "Parsed contact details successfully");
//                    Log.d(TAG, "contactsArray=" + contacts);

                } catch (JSONException e) {
//                    Log.e(TAG, "No value for starred attribute was returned.");
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
//            Log.e(TAG, "Failed to parse contact details successfully", e);
        }

//        Contact.nextNotificationId = 1;
        return contacts;
    }

    /**
     * Verifies resource created successfully (201 response).
     *
     * @param responseBody
     * @return
     */
    public boolean parseCreateResourceResponse(String responseBody) {
        boolean createdSuccessfully = false;
//        Log.d(TAG, "Requesting to create resource");

        try {
            JSONObject responseObject = new JSONObject(responseBody);
//            Log.d(TAG, "responseObject=" + responseObject);

            int status = responseObject.getInt(STATUS_TAG);
            String message = responseObject.getString(MESSAGE_TAG);

            if (status == 0 && message.equalsIgnoreCase("created")) {
                createdSuccessfully = true;
//                Log.d(TAG, "Created resource successfully");
            }

        } catch (JSONException e) {
//            Log.e(TAG, "Failed to create resource successfully");
            e.printStackTrace();
        }
        return createdSuccessfully;
    }
}
