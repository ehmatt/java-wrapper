package com.onepagecrm.models.fabricators;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
public class ContactFabricatorTest extends BaseTest {

    public void testSingle_correctValues() {
        validateSingle(ContactFabricator.single());
    }

    public void testImportContact_fieldsNotNull() {
        Contact fabricated = ContactFabricator.importContact();
        assertNotNull("This field cannot be null.", fabricated.getFirstName());
        assertNotNull("This field cannot be null.", fabricated.getLastName());
        assertNotNull("This field cannot be null.", fabricated.getCompanyName());
        assertNotNull("This field cannot be null.", fabricated.getJobTitle());
        assertNotNull("This field cannot be null.", fabricated.getBackground());
        assertNotNull("This field cannot be null.", fabricated.isStarred());
        assertNotNull("This field cannot be null.", fabricated.getAddress());
        assertNotNull("This field cannot be null.", fabricated.getPhones());
        assertNotNull("This field cannot be null.", fabricated.getEmails());
        assertNotNull("This field cannot be null.", fabricated.getUrls());
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public void testList_allContactsValid() {
        ContactList contacts = ContactFabricator.list();
        assertEquals("Should be 10 contacts", 10, contacts.size());

        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (i == 0) validateSingle(contact);
            else assertTrue("Contact not valid.", contact.isValid());
        }
    }

    public void testList_contactsPartial() {
        ContactList contacts = ContactFabricator.contactsPartial();
        assertEquals("Should be 3 contacts", 3, contacts.size());

        for (Contact contact : contacts) {
            assertTrue("Contact not valid.", contact.isValid());
        }
    }

    public void testList_actionStreamPartial() {
        ContactList contacts = ContactFabricator.actionStreamPartial();
        assertEquals("Should be 3 contacts", 3, contacts.size());

        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (i == 0) validateSingle(contact);
            else assertTrue("Contact not valid.", contact.isValid());
        }
    }

    private void validateSingle(Contact contact) {
        assertTrue("Contact not valid.", contact.isValid());
        assertEquals("56fa81eb9007ba07fc000080", contact.getId());
        assertEquals("Java", contact.getFirstName());
        assertEquals("Wrapper", contact.getLastName());
        assertEquals("Software Developer", contact.getJobTitle());
        assertEquals("OnePageCRM", contact.getCompanyName());
        assertEquals("56fd40b49007ba716f000004", contact.getCompanyId());
        assertEquals("Java is very verbose, we met in college.", contact.getBackground());
        assertTrue(contact.isStarred());
        assertEquals("559cd1866f6e656707000001", contact.getOwnerId());
        assertEquals("advertisement", contact.getLeadSourceId());
        assertEquals("Lead", contact.getStatus());
        assertEquals("559cd19f6f6e656707000005", contact.getStatusId());
        assertTrue(contact.hasPendingDeal());
        assertEquals(20499.99d, contact.getTotalPending());
        assertNotNull("This field cannot be null.", contact.getAddress());
        assertNotNull("This field cannot be null.", contact.getPhones());
        assertNotNull("This field cannot be null.", contact.getEmails());
        assertNotNull("This field cannot be null.", contact.getUrls());
    }
}
