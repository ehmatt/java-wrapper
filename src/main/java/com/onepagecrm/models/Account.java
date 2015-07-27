package com.onepagecrm.models;

import java.util.List;

import com.onepagecrm.models.internal.ContactsCount;
import com.onepagecrm.models.internal.Settings;
import com.onepagecrm.models.internal.TeamStream;

public class Account {

	public static List<User> team;
	public static User loggedInUser;
	public static Settings settings;

	public List<CustomField> customFields;
	public List<CallResult> callResults;
	public List<Tag> tags;
	public List<Status> statuses;
	public List<LeadSource> leadSources;

	public TeamStream teamStream;
	public ContactsCount contactsCount;

	// public static User switchUser(login, password) {
	
}
