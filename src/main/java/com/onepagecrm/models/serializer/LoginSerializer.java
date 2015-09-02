package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.User;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginSerializer extends BaseSerializer {

    public static User fromString(String response) {
        Account.loginResponse = response;
        Account.loggedInUser = UserSerializer.fromString(response);
        // Account.team.add(Account.loggedInUser);
        return Account.loggedInUser;
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