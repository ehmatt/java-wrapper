package com.onepagecrm.models.serializer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import com.onepagecrm.models.CallResult;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.User;

public class UserSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactList.class.getName());

    public static User fromString(String userString) {
        try {
            JSONObject responseObject = new JSONObject(userString);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);

            String userId = dataObject.getString(USER_ID_TAG);
            String authKey = dataObject.getString(AUTH_KEY_TAG);
            String accountType = dataObject.getString(ACCOUNT_TYPE_TAG);

            User user = new User()
                    .setId(userId)
                    .setAuthKey(authKey)
                    .setAccountType(accountType);

            user = addCallResults(dataObject, user);

            JSONObject outsideUserObject = dataObject.getJSONObject(USER_TAG);
            JSONObject userObject = outsideUserObject.getJSONObject(USER_TAG);
            return fromJson(userObject, user);

        } catch (JSONException e) {
            LOG.severe("Error parsing user response");
            LOG.severe(e.toString());
            return new User();
        }
    }

    public static User fromJson(JSONObject userObject, User user) {
        try {
            user.setFirstName(userObject.getString(FIRST_NAME_TAG))
                    .setLastName(userObject.getString(LAST_NAME_TAG))
                    .setEmail(userObject.getString(EMAIL_TAG))
                    .setCompanyName(userObject.getString(COMPANY_NAME_TAG))
                    .setPhotoUrl(userObject.getString(PHOTO_URL_TAG))
                    .setBccEmail(userObject.getString(BCC_EMAIL_TAG));
            return user;
        } catch (JSONException e) {
            LOG.severe("Error parsing user JSON object");
            LOG.severe(e.toString());
            return new User();
        }
    }

    public JSONObject toJsonObject(User user) {
        return new JSONObject();
    }

    public static User addCallResults(JSONObject dataObject, User user) {
        ArrayList<CallResult> callResults = new ArrayList<>();
        int index = 0;
        try {
            JSONObject callResultsObject = dataObject.getJSONObject("call_results");
            Iterator<String> iterator = callResultsObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                try {
                    Object value = callResultsObject.get(key);
                    callResults.add(new CallResult(index, key, value.toString()));
                } catch (JSONException e) {
                    LOG.severe("Failed to parse all values in call_results object");
                    LOG.severe(e.toString());
                }
                index++;
            }
        } catch (JSONException e) {
            LOG.severe("No call_results JSON object in response");
            LOG.severe(e.toString());
            // TODO : remove this fabricated call results list
            callResults.add(new CallResult(0, "interested", "Interested"));
            callResults.add(new CallResult(1, "not_interested", "Not interested"));
            callResults.add(new CallResult(2, "left_message", "Left message"));
            callResults.add(new CallResult(3, "no_answer", "No answer"));
            callResults.add(new CallResult(4, "other", "Other"));
        }
        return user.setCallResults(callResults);
    }
}
