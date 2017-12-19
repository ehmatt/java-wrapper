package com.onepagecrm.models;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.fabricators.ContactFabricator;
import com.onepagecrm.models.internal.Paginator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ContactListTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(ContactListTest.class.getName());

    private ContactList allContacts;
    private Contact tigerWoods;
    private Contact cillianMyles;
    private Contact janeDoe;
    private Contact joeBloggs;
    private Contact johnSmith;
    private Contact maryDempsey;

    /**
     * Manual creation of mock objects:
     * <li>Contacts with phone numbers</li>
     * <li>Contacts without phone numbers</li>
     * <li>ArrayList of these Contacts</li>
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        allContacts = new ContactList(new ArrayList<Contact>());

        List<Phone> tigersPhones = new ArrayList<>();
        tigersPhones.add(0, new Phone("work", "0862524363"));
        tigerWoods = new Contact()
                .setId("55804f6b1787fa72b400002e")
                .setOwnerId("556cb8b61787fa02e000047e")
                .setFirstName("Tiger")
                .setLastName("Woods")
                .setPhones(tigersPhones)
                .setCompanyName("Myles Inc.");
        allContacts.add(0, tigerWoods);

        List<Phone> cilliansPhones = new ArrayList<>();
        cilliansPhones.add(0, new Phone("work", "6178187423"));
        cillianMyles = new Contact()
                .setId("558037511787fa21ba000148")
                .setOwnerId("556cb8b61787fa02e000047e")
                .setFirstName("Cillian")
                .setLastName("Myles")
                .setPhones(cilliansPhones)
                .setCompanyName("Myles Inc.");
        allContacts.add(1, cillianMyles);

        List<Phone> janesPhones = new ArrayList<>();
        janesPhones.add(0, new Phone("work", "312 34 4491223"));
        janeDoe = new Contact()
                .setId("556cb8c91787fa0e24000017")
                .setOwnerId("556cb8b61787fa02e000047e")
                .setFirstName("Jane")
                .setLastName("Doe")
                .setPhones(janesPhones)
                .setCompanyName("Big Company Inc.");
        allContacts.add(2, janeDoe);

        List<Phone> joesPhones = new ArrayList<>();
        joesPhones.add(0, new Phone("work", "+1 23 978234"));
        joesPhones.add(1, new Phone("mobile", "+1 213 555 1307"));
        joesPhones.add(2, new Phone("work", "+353 85 8187777"));
        joesPhones.add(3, new Phone("mobile", "+1 284 3827 111"));
        joeBloggs = new Contact()
                .setId("556cb8c91787fa0e2400002a")
                .setOwnerId("556cb8b61787fa02e000047e")
                .setFirstName("Joe")
                .setLastName("Bloggs")
                .setPhones(joesPhones)
                .setCompanyName("Acme Inc.");
        allContacts.add(3, joeBloggs);

        johnSmith = new Contact()
                .setId("556cb8c91787fa0e240fff17")
                .setOwnerId("556cb8b61787fa02e000047e")
                .setFirstName("John")
                .setLastName("Smith")
                .setCompanyName("Big Company Inc.");
        allContacts.add(4, johnSmith);

        maryDempsey = new Contact()
                .setId("556cb8c91787fa0e24efff17")
                .setOwnerId("556cb8b61787fa02e000047e")
                .setFirstName("Mary")
                .setLastName("Dempsey")
                .setCompanyName("Big Company Inc.");
        allContacts.add(5, maryDempsey);
    }

    /**
     * Method should return list of Contacts with phone numbers.
     * <p/>
     * Given list of Contacts, some with / some without.
     * Answer should be subset of Contacts which do have numbers.
     */
    public void testGetPhoneableContacts_SomeContactsHavePhones() {
        ContactList someContactsWithPhones = new ContactList();
        someContactsWithPhones.add(tigerWoods);
        someContactsWithPhones.add(cillianMyles);
        someContactsWithPhones.add(janeDoe);
        someContactsWithPhones.add(joeBloggs);
        someContactsWithPhones.add(johnSmith);
        someContactsWithPhones.add(maryDempsey);

        int numberWithPhones = 0;
        for (int i = 0; i < someContactsWithPhones.size(); i++) {
            List<Phone> phones = someContactsWithPhones.get(i).getPhones();
            if (phones != null && !phones.isEmpty()) {
                numberWithPhones++;
            }
        }
        assertEquals(numberWithPhones,
                someContactsWithPhones.getPhoneableContacts().size());
    }

    /**
     * Method should return list of Contacts with phone numbers.
     * <p/>
     * Given list of Contacts, all of which have phone numbers.
     * Answer should be the entire list of Contacts which was sent.
     */
    public void testGetPhoneableContacts_AllContactsHavePhones() {
        ContactList allContactsWithPhones = new ContactList();
        allContactsWithPhones.add(tigerWoods);
        allContactsWithPhones.add(cillianMyles);
        allContactsWithPhones.add(janeDoe);
        allContactsWithPhones.add(joeBloggs);
        assertEquals(allContactsWithPhones.size(),
                allContactsWithPhones.getPhoneableContacts().size());
    }

    /**
     * Method should return list of Contacts with phone numbers.
     * <p/>
     * Given list of Contacts, none of which have phone numbers.
     * Answer should be empty list of Contacts.
     */
    public void testGetPhoneableContacts_NoContactsHavePhones() {
        ContactList allContactsWithoutPhones = new ContactList();
        allContactsWithoutPhones.add(johnSmith);
        allContactsWithoutPhones.add(maryDempsey);
        assertEquals(0, allContactsWithoutPhones.getPhoneableContacts().size());
    }

    /**
     * Searching through Contacts for number which does not exist.
     */
    public void testInActionStream_False() {
        // Number is not belong to any Contacts in AS.
        Contact contact = allContacts.inActionStream("0858187423");

        // Should be a new (empty) contact.
        assertNotNull(contact);

        // With no id
        assertFalse(contact.isValid());
    }

    /**
     * Searching through Contacts for Tiger Woods' number, he [Contact object]
     * should be returned.
     * <p/>
     * In app, this would generate a notification.
     */
    public void testInActionStream_True() {
        // Number is belong to Tiger Woods from AS.
        assertEquals(tigerWoods, allContacts.inActionStream("0862524363"));
    }

    /**
     * Verifying correct operation of getNextContact method.
     * <p/>
     * Will move through the list and jump to start if reaches the end.
     */
    public void testGetNextContact_CorrectAnswer() {
        assertEquals(cillianMyles, allContacts.getNextContact(0));
        assertEquals(janeDoe, allContacts.getNextContact(1));
        assertEquals(joeBloggs, allContacts.getNextContact(2));
        assertEquals(johnSmith, allContacts.getNextContact(3));
        assertEquals(maryDempsey, allContacts.getNextContact(4));
        assertEquals(tigerWoods, allContacts.getNextContact(5));
    }

    /**
     * Verifying correct operation of getPreviousContact method.
     * <p/>
     * Will move backwards through the list and jump to end if positioned at the start.
     */
    public void testGetPreviousContact_CorrectAnswer() {
        assertEquals(johnSmith, allContacts.getPreviousContact(5));
        assertEquals(joeBloggs, allContacts.getPreviousContact(4));
        assertEquals(janeDoe, allContacts.getPreviousContact(3));
        assertEquals(cillianMyles, allContacts.getPreviousContact(2));
        assertEquals(tigerWoods, allContacts.getPreviousContact(1));
        assertEquals(maryDempsey, allContacts.getPreviousContact(0));
    }

    /**
     * Verifying correct operation of getArrayPosition method.
     */
    public void testGetArrayPosition_CorrectAnswers() {
        assertEquals(0, allContacts.getArrayPosition(tigerWoods));
        assertEquals(1, allContacts.getArrayPosition(cillianMyles));
        assertEquals(2, allContacts.getArrayPosition(janeDoe));
        assertEquals(3, allContacts.getArrayPosition(joeBloggs));
        assertEquals(4, allContacts.getArrayPosition(johnSmith));
        assertEquals(5, allContacts.getArrayPosition(maryDempsey));
    }

    public void testContactList_Iterable() {
        ContactList contacts = ContactFabricator.list();
        // Create correct lists using regular old for loop.
        List<String> contactIds = new ArrayList<>();
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            contactIds.add(i, contacts.get(i).getId());
            positions.add(i, i);
        }

        // Now check that foreach loop gives the same answers.
        // This tests implementation of Iterator since foreach calls this internally.
        boolean iteratorUsed = false;
        int counter = 0;
        for (Contact contact : contacts) {
            iteratorUsed = true;
            int index = contacts.indexOf(contact);
            // Check order.
            assertEquals(counter, index);
            // Verify ids.
            assertEquals(contactIds.get(index), contact.getId());
            // Verify positions.
            assertEquals(((int) positions.get(index)), counter);
            counter++;
        }
        assertTrue("Did not iterate over ContactList.\n", iteratorUsed);
    }

    public void testContactList_PaginatorSetUp() {
        Paginator lPaginator = new ContactList().getPaginator();
        assertNotNull("Did not create new Pagintator object for ContactList.\n", lPaginator);
        assertNotNull("Current page should not be null.\n", lPaginator.getCurrentPage());
        assertNotNull("Max page should not be null.\n", lPaginator.getMaxPage());
        assertNotNull("Per page should not be null.\n", lPaginator.getPerPage());
    }
}
