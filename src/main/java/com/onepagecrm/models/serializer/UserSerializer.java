package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.CallResult;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

public class UserSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactList.class.getName());

    public static User fromString(String dataString) {
        try {
            JSONObject dataObject = new JSONObject(dataString);
            String userId = dataObject.getString(USER_ID_TAG);
            String authKey = dataObject.getString(AUTH_KEY_TAG);
            String accountType = dataObject.getString(ACCOUNT_TYPE_TAG);

            User user = new User()
                    .setId(userId)
                    .setAuthKey(authKey)
                    .setAccountType(accountType);

            user.setAccount(new Account()
                    .setCustomFields(
                            CustomFieldSerializer.fromJsonArray(dataObject.getJSONArray(CUSTOM_FIELDS_TAG))
                    )
            );

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
            if (dataObject.has(CALL_RESULTS_TAG)) {
                JSONObject callResultsObject = dataObject.getJSONObject("call_results");
                Iterator<String> iterator = callResultsObject.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    try {
                        Object value = callResultsObject.get(key);
                        callResults.add(new CallResult()
                                        .setIntId(index)
                                        .setId(key)
                                        .setDisplay(value.toString())
                        );
                    } catch (JSONException e) {
                        LOG.severe("Failed to parse all values in call_results object");
                        LOG.severe(e.toString());
                    }
                    index++;
                }
            } else {
                callResults.add(new CallResult()
                                .setIntId(0)
                                .setId("interested")
                                .setDisplay("Interested")
                );
                callResults.add(new CallResult()
                                .setIntId(1)
                                .setId("not_interested")
                                .setDisplay("Not interested")
                );
                callResults.add(new CallResult()
                                .setIntId(2)
                                .setId("left_message")
                                .setDisplay("Left message")
                );
                callResults.add(new CallResult()
                                .setIntId(3)
                                .setId("no_answer")
                                .setDisplay("No answer")
                );
                callResults.add(new CallResult()
                                .setIntId(4)
                                .setId("other")
                                .setDisplay("Other")
                );
            }
        } catch (JSONException e) {
            LOG.severe("No call_results JSON object in response");
            LOG.severe(e.toString());
        }
        return user.setCallResults(callResults);
    }
}
