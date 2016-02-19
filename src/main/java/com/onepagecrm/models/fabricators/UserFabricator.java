package com.onepagecrm.models.fabricators;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Account;
import com.onepagecrm.models.User;
import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.models.serializers.LoginSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class UserFabricator extends BaseFabricator {

    private static final Logger LOG = Logger.getLogger(UserFabricator.class.getName());

    public static User single() {
        return loggedUser();
    }

    public static User basicUser() {
        return new User()
                .setId("556cb8b61787fa02e000047e")
                .setAuthKey("WqLLs1n/Y3SvOpGg5CNOpdKy74GkGI6lnhwSfYmgNl4=")
                .setFirstName("John")
                .setLastName("Smith")
                .setEmail("j.smith@bigcompanyinc.com");
    }

    public static List<User> list() {
        List<User> users = new ArrayList<>();
        User loggedUser = loggedUser();
        users.add(loggedUser);
        for (User member : Account.team) {
            if (!users.contains(member)) users.add(member);
        }
        return users;
    }

//    private static User loggedUser() {
//        User loggedUser = new User();
//        Utilities utils = new Utilities();
//        String loginResponse = utils.getResourceContentsFor("DEV-login.json");
//        try {
//            loggedUser = LoginSerializer.fromString(loginResponse);
//        } catch (OnePageException e) {
//            LOG.severe("Problem creating user object from JSON file.");
//            LOG.severe(e.toString());
//        }
//        return loggedUser;
//    }

    private static User loggedUser() {
        User loggedUser = new User();
        String loginResponse = Utilities.getResourceContents(
                "src/test/res/responses/perfect/DEV-login.json");
        try {
            loggedUser = LoginSerializer.fromString(loginResponse);
        } catch (OnePageException e) {
            LOG.severe("Problem creating user object from JSON file.");
            LOG.severe(e.toString());
        }
        return loggedUser;
    }
}
