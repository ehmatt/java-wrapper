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

    public void testSingle_correctValues() {
        Contact fabricated = ContactFabricator.single();
        assertTrue("Contact not valid.", fabricated.isValid());
        assertEquals("5694e1999007ba687b0000c9", fabricated.getId());
        assertEquals("Java", fabricated.getFirstName());
        assertEquals("Wrapper", fabricated.getLastName());
        assertEquals("Software Developer", fabricated.getJobTitle());
        assertEquals("OnePageCRM", fabricated.getCompanyName());
        assertEquals("Java is very verbose, we met in college.", fabricated.getBackground());
        assertTrue(fabricated.isStarred() != null && fabricated.isStarred());
        assertEquals("559cd1866f6e656707000001", fabricated.getOwnerId());
        assertEquals("email_web", fabricated.getLeadSourceId());
        assertEquals("Prospect", fabricated.getStatus());
        assertEquals("559cd1a06f6e656707000006", fabricated.getStatusId());
        assertTrue(fabricated.hasPendingDeal() != null && fabricated.hasPendingDeal());
        assertEquals(20400d, fabricated.getTotalPending());
        assertNotNull("This field cannot be null.", fabricated.getAddress());
        assertNotNull("This field cannot be null.", fabricated.getPhones());
        assertNotNull("This field cannot be null.", fabricated.getEmails());
        assertNotNull("This field cannot be null.", fabricated.getUrls());
    }

    public void testFromAddressBook_fieldsNotNull() {
        Contact fabricated = ContactFabricator.importContact();
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
        assertEquals("Should be 10 contacts", contacts.size(), 10);

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
