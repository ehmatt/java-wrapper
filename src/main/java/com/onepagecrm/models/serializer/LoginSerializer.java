package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.User;

public class LoginSerializer extends BaseSerializer {

    public static User parseLogin(String response) {

        Account.loginResponse = response;
        Account.loggedInUser = UserSerializer.fromString(response);
        // Account.team.add(Account.loggedInUser);

        return Account.loggedInUser;
    }
}