package com.onepagecrm.api.models;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.util.ArrayList;


public class ContactListTest extends TestCase {

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
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        allContacts = new ContactList(new ArrayList<Contact>());

        ArrayList<Phone> tigersPhones = new ArrayList<>();
        tigersPhones.add(0, new Phone("work", "0862524363"));
        tigerWoods = new Contact("55804f6b1787fa72b400002e", "556cb8b61787fa02e000047e",
                "Tiger", "Woods", tigersPhones, "Myles Inc.");
        allContacts.add(0, tigerWoods);

        ArrayList<Phone> cilliansPhones = new ArrayList<>();
        cilliansPhones.add(0, new Phone("work", "6178187423"));
        cillianMyles = new Contact("558037511787fa21ba000148", "556cb8b61787fa02e000047e",
                "Cillian", "Myles", cilliansPhones, "Myles Inc.");
        allContacts.add(1, cillianMyles);

        ArrayList<Phone> janesPhones = new ArrayList<>();
        janesPhones.add(0, new Phone("work", "312 34 4491223"));
        janeDoe = new Contact("556cb8c91787fa0e24000017", "556cb8b61787fa02e000047e",
                "Jane", "Doe", janesPhones, "Big Company Inc.");
        allContacts.add(2, janeDoe);

        ArrayList<Phone> joesPhones = new ArrayList<>();
        joesPhones.add(0, new Phone("work", "+1 23 978234"));
        joesPhones.add(1, new Phone("mobile", "+1 213 555 1307"));
        joesPhones.add(2, new Phone("work", "+353 85 8187777"));
        joesPhones.add(3, new Phone("mobile", "+1 284 3827 111"));
        joeBloggs = new Contact("556cb8c91787fa0e2400002a", "556cb8b61787fa02e000047e",
                "Joe", "Bloggs", joesPhones, "Acme Inc.");
        allContacts.add(3, joeBloggs);

        johnSmith = new Contact("556cb8c91787fa0e240fff17", "556cb8b61787fa02e000047e",
                "John", "Smith", new ArrayList<Phone>(), "Big Company Inc.");
        allContacts.add(4, johnSmith);

        maryDempsey = new Contact("556cb8c91787fa0e24efff17", "556cb8b61787fa02e000047e",
                "Mary", "Dempsey", new ArrayList<Phone>(), "Big Company Inc.");
        allContacts.add(5, maryDempsey);
    }

    /**
     * Method should return list of Contacts with phone numbers.
     *
     * Given list of Contacts, some with / some without.
     * Answer should be subset of Contacts which do have numbers.
     */
    @SmallTest
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
            if (!someContactsWithPhones.get(i).getPhones().isEmpty()) {
                numberWithPhones++;
            }
        }
        assertEquals(numberWithPhones,
                someContactsWithPhones.getPhoneableContacts().size());
    }

    /**
     * Method should return list of Contacts with phone numbers.
     *
     * Given list of Contacts, all of which have phone numbers.
     * Answer should be the entire list of Contacts which was sent.
     */
    @SmallTest
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
     *
     * Given list of Contacts, none of which have phone numbers.
     * Answer should be empty list of Contacts.
     */
    @SmallTest
    public void testGetPhoneableContacts_NoContactsHavePhones() {
        ContactList allContactsWithoutPhones = new ContactList();
        allContactsWithoutPhones.add(johnSmith);
        allContactsWithoutPhones.add(maryDempsey);
        assertEquals(0, allContactsWithoutPhones.getPhoneableContacts().size());
    }

    /**
     * Searching through Contacts for number which does not exist.
     */
    @SmallTest
    public void testInActionStream_False() {
        // Number is not belong to any Contacts in AS.
        assertEquals(null, allContacts.inActionStream("0858187423").getId());
    }

    /**
     * Searching through Contacts for Tiger Woods' number, he [Contact object]
     * should be returned.
     *
     * In app, this would generate a notification.
     */
    @SmallTest
    public void testInActionStream_True() {
        // Number is belong to Tiger Woods from AS.
        assertEquals(tigerWoods, allContacts.inActionStream("0862524363"));
    }

    /**
     * Verifying correct operation of getNextContact method.
     *
     * Will move through the list and jump to start if reaches the end.
     */
    @SmallTest
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
     *
     * Will move backwards through the list and jump to end if positioned at the start.
     */
    @SmallTest
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
    @SmallTest
    public void testGetArrayPosition_CorrectAnswers() {
        assertEquals(0, allContacts.getArrayPosition(tigerWoods));
        assertEquals(1, allContacts.getArrayPosition(cillianMyles));
        assertEquals(2, allContacts.getArrayPosition(janeDoe));
        assertEquals(3, allContacts.getArrayPosition(joeBloggs));
        assertEquals(4, allContacts.getArrayPosition(johnSmith));
        assertEquals(5, allContacts.getArrayPosition(maryDempsey));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
