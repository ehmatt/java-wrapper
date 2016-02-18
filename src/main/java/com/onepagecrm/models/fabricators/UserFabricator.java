package com.onepagecrm.models.fabricators;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.User;
import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.models.serializers.LoginSerializer;

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
