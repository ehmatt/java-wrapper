package com.onepagecrm.models.fabricators;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
public class ContactFabricatorTest extends BaseTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testFromAddressBook_fieldsNotNull() {
        Contact fabricated = ContactFabricator.fromAddressBook();
        assertNotNull("This field cannot be null.", fabricated.getFirstName());
        assertNotNull("This field cannot be null.", fabricated.getLastName());
        assertNotNull("This field cannot be null.", fabricated.getCompanyName());
        assertNotNull("This field cannot be null.", fabricated.getJobTitle());
        assertNotNull("This field cannot be null.", fabricated.getBackground());
        assertNotNull("This field cannot be null.", fabricated.getPhotoUrl());
        assertNotNull("This field cannot be null.", fabricated.isStarred());
        assertNotNull("This field cannot be null.", fabricated.getAddress());
        assertNotNull("This field cannot be null.", fabricated.getPhones());
        assertNotNull("This field cannot be null.", fabricated.getEmails());
        assertNotNull("This field cannot be null.", fabricated.getUrls());
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public void testList_allContactsValid() {
        ContactList contacts = ContactFabricator.list();
        assertEquals("Should be 18 contacts", contacts.size(), 18);

        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            assertTrue("Contact not valid.", contact.isValid());
        }
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
