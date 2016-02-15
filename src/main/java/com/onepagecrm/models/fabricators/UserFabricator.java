package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.User;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class UserFabricator extends BaseFabricator {

    public static User basicUser() {
        return new User()
                .setId("556cb8b61787fa02e000047e")
                .setAuthKey("WqLLs1n/Y3SvOpGg5CNOpdKy74GkGI6lnhwSfYmgNl4=")
                .setFirstName("John")
                .setLastName("Smith")
                .setEmail("j.smith@bigcompanyinc.com");
    }
}
