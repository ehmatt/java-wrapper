package com.onepagecrm.models.fabricators;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.User;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
public class UserFabricatorTest extends BaseTest {

    public void testSingle_correctValues() {
        User user = UserFabricator.single();
        assertTrue("Contact not valid.", user.isValid());
        assertEquals("559cd1866f6e656707000001", user.getId());
        assertEquals("xbigujk/IWwGwz0ojZAXnl/jOiQ3yKnBAFOWM6F9c88=", user.getAuthKey());
        assertEquals("pro", user.getAccountType());
        assertEquals("Cillian", user.getFirstName());
        assertEquals("Myles", user.getLastName());
        assertEquals("559cd1866f6e656707000000@users.onepagecrm.com", user.getBccEmail());
        assertEquals("Myles Inc.", user.getCompanyName());
        assertEquals("", user.getPhotoUrl());
    }

    public void testBasicUser_correctValues() {
        User basicUser = UserFabricator.basicUser();
        assertTrue("Contact not valid.", basicUser.isValid());
        assertEquals("556cb8b61787fa02e000047e", basicUser.getId());
        assertEquals("WqLLs1n/Y3SvOpGg5CNOpdKy74GkGI6lnhwSfYmgNl4=", basicUser.getAuthKey());
        assertEquals("John", basicUser.getFirstName());
        assertEquals("Smith", basicUser.getLastName());
        assertEquals("j.smith@bigcompanyinc.com", basicUser.getEmail());
    }
}
