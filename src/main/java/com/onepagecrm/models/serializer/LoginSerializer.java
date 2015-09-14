package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Account;
import com.onepagecrm.models.User;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginSerializer extends BaseSerializer {

    public static User fromString(String responseBody) throws OnePageException {
        Account.loginResponse = responseBody;
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            Account.loggedInUser = UserSerializer.fromString(parsedResponse);
            return Account.loggedInUser;

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;
        }
    }

    public static String toJsonObject(String username, String password) {
        JSONObject loginObject = new JSONObject();
        try {
            loginObject.put(LOGIN_TAG, username);
            loginObject.put(PASSWORD_TAG, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loginObject.toString();
    }
}