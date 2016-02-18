package com.onepagecrm.models.fabricators;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.User;

import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
public class UserFabricatorTest extends BaseTest {

    public void testSingle_correctValues() {
        validateSingle(UserFabricator.single());
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

    public void testList_correctValues() {
        List<User> users = UserFabricator.list();
        assertEquals("Should be 2 users", users.size(), 2);

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (i == 0) validateSingle(user);
            else validateSecond(user);
        }
    }

    private void validateSingle(User user) {
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

    private void validateSecond(User user) {
        assertFalse("Contact shouldn't be valid.", user.isValid());
        assertEquals("561b937f9007ba4cc200004e", user.getId());
        assertEquals(null, user.getAuthKey());
        assertEquals("John", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals(null, user.getBccEmail());
        assertEquals("", user.getCompanyName());
        assertEquals("", user.getPhotoUrl());
    }
}
