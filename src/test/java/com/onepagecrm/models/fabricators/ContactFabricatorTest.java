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
        assertEquals("Should be 10 contacts", contacts.size(), 10);

        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (i == 0) validateSingle(contact);
            else assertTrue("Contact not valid.", contact.isValid());
        }
    }

    private void validateSingle(Contact contact) {
        assertTrue("Contact not valid.", contact.isValid());
        assertEquals("56c3510d9007ba7dab0000cb", contact.getId());
        assertEquals("Java", contact.getFirstName());
        assertEquals("Wrapper", contact.getLastName());
        assertEquals("Software Developer", contact.getJobTitle());
        assertEquals("OnePageCRM", contact.getCompanyName());
        assertEquals("56c351089007ba7dab000058", contact.getCompanyId());
        assertEquals("Java is very verbose, we met in college.", contact.getBackground());
        assertTrue(contact.isStarred() != null && contact.isStarred());
        assertEquals("559cd1866f6e656707000001", contact.getOwnerId());
        assertEquals("email_web", contact.getLeadSourceId());
        assertEquals("Prospect", contact.getStatus());
        assertEquals("559cd1a06f6e656707000006", contact.getStatusId());
        assertTrue(contact.hasPendingDeal() != null && contact.hasPendingDeal());
        assertEquals(20400d, contact.getTotalPending());
        assertNotNull("This field cannot be null.", contact.getAddress());
        assertNotNull("This field cannot be null.", contact.getPhones());
        assertNotNull("This field cannot be null.", contact.getEmails());
        assertNotNull("This field cannot be null.", contact.getUrls());
    }
}
