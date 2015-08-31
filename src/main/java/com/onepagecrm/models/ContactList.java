package com.onepagecrm.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactList extends ArrayList<Contact> implements Serializable {

    private static final long serialVersionUID = 8185938052776557364L;

    private List<Contact> contacts;

    public ContactList(List<Contact> contacts) {
	this.contacts = new ArrayList<>();
	if (contacts != null && !contacts.isEmpty()) {
	    for (int i = 0; i < contacts.size(); i++) {
		this.contacts.add(contacts.get(i));
	    }
	}
    }

    public ContactList() {
	this.contacts = new ArrayList<>();
    }

    public String toString() {
	return contacts.toString();
    }

    public boolean isEmpty() {
	return contacts.isEmpty();
    }

    public int size() {
	return contacts.size();
    }

    public List<Contact> getContacts() {
	return contacts;
    }

    public void setContacts(List<Contact> contacts) {
	this.contacts = new ArrayList<>();
	for (int i = 0; i < contacts.size(); i++) {
	    this.contacts.add(contacts.get(i));
	}
    }

    public void add(int index, Contact contact) {
	contacts.add(index, contact);
    }

    public Contact get(int index) {
	return contacts.get(index);
    }

    /**
     * Determines mContacts which are phone-able i.e. have at least 1 phone
     * number.
     * <p/>
     * Takes a list of mContacts. Returns a list of Contacts which have phone
     * numbers.
     *
     * @return
     */
    public ContactList getPhoneableContacts() {
	ArrayList<Contact> phoneableContacts = new ArrayList<>();
	int counter = 0;
	for (int i = 0; i < contacts.size(); i++) {
	    if (!contacts.get(i).getPhones().isEmpty() && contacts.get(i).getPhones() != null) {
		contacts.get(i).setIntId(counter + 1);
		phoneableContacts.add(contacts.get(i));
		counter++;
	    }
	}
	return new ContactList(phoneableContacts);
    }

    /**
     * Determines mContacts which STARRED contacts are phone-able i.e. have at
     * least 1 phone number.
     * <p/>
     * Takes a list of mContacts. Returns a list of Contacts which have phone
     * numbers.
     *
     * @return
     */
    public ContactList getStarredPhoneableContacts() {
	ArrayList<Contact> phoneableContacts = new ArrayList<>();
	int counter = 0;
	for (int i = 0; i < contacts.size(); i++) {
	    if (contacts.get(i).isStarred()) {
		if (!contacts.get(i).getPhones().isEmpty() && contacts.get(i).getPhones() != null) {
		    contacts.get(i).setIntId(counter + 1);
		    phoneableContacts.add(contacts.get(i));
		    counter++;
		}
	    }
	}
	return new ContactList(phoneableContacts);
    }

    /**
     * Checks if a phone number exists in action stream.
     * <p/>
     * Takes a list of mContacts Returns the Contact object if the phone numbers
     * match.
     *
     * @param incomingNumber
     * @return
     */
    public Contact inActionStream(String incomingNumber) {
	for (int i = 0; i < contacts.size(); i++) {
	    List<Phone> phones = contacts.get(i).getPhones();
	    if (phones != null && !phones.isEmpty()) {
		for (int j = 0; j < contacts.get(i).getPhones().size(); j++) {
		    if (incomingNumber.equals(contacts.get(i).getPhones().get(j).getNumber())) {
			return contacts.get(i);
		    }
		}
	    }
	}
	return new Contact();
    }

    /**
     * Simply gets the next Contact in the list.
     * <p/>
     * If at end of list, jumps back to the start.
     *
     * @param position
     * @return
     */
    public Contact getNextContact(int position) {
	if (contacts != null && !contacts.isEmpty()) {
	    int length = contacts.size();
	    int newPosition;
	    if (position < (length - 1)) {
		newPosition = position + 1;
	    } else {
		newPosition = 0;
	    }
	    return contacts.get(newPosition);
	} else {
	    return new Contact();
	}
    }

    /**
     * Simply gets the previous contact in the list.
     * <p/>
     * If at the start, jumps back to the end.
     *
     * @param position
     * @return
     */
    public Contact getPreviousContact(int position) {
	if (contacts != null && !contacts.isEmpty()) {
	    int length = contacts.size();
	    int newPosition;
	    if (position > 0) {
		newPosition = position - 1;
	    } else {
		newPosition = length - 1;
	    }
	    return contacts.get(newPosition);
	} else {
	    return new Contact();
	}
    }

    /**
     * Get the array index of the given contact.
     *
     * @param contact
     * @return
     */
    public int getArrayPosition(Contact contact) {
	int position = -1;
	for (int i = 0; i < contacts.size(); i++) {
	    if (contact.getId().equals(contacts.get(i).getId())) {
		position = i;
	    }
	}
	return position;
    }
}
