package com.onepagecrm.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.onepagecrm.net.ApiResource;


public class Contact extends ApiResource implements Serializable {

	private static final long serialVersionUID = -6073805195226829625L;

	private String id;

	private int intId;
	public static int nextIntId = 1;

	private String ownerId;
	private String firstName;
	private String lastName;
	private String photoUrl;
	private String jobTitle;
	private String background;
	private List<URL> urls;
	private List<Phone> phones;
	private List<Email> emails;
	private String status;
	private String statusId;
	private boolean starred;

	private String leadSourceId;
	// private LeadSource leadSource;

	private boolean pendingDeal;
	private String type;
	private String companyName;
	private String companyId;

	// "sale_closed_for": [ ]

	private List<Tag> tags;
	private List<CustomField> customFields;
	private String createdAt;
	private String modifiedAt;

	private List<String> addressLines;
	// private Address address;

	public Contact(String id, String ownerId, String firstName, String lastName, ArrayList<Phone> phones,
			String companyName) {

		this.id = id;
		this.intId = nextIntId;
		nextIntId++;

		this.ownerId = ownerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phones = phones;
		this.companyName = companyName;
	}

	/**
	 * Default blank constructor.
	 */
	public Contact() {
		this.intId = nextIntId;
		nextIntId++;
	}
	
	public boolean isValid() {
		return this.id != null && !this.id.equals("");
	}

	public int getIntId() {
		return intId;
	}

	public String getId() {
		return id;
	}

	public Contact setId(String id) {
		this.id = id;
		return this;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public Contact setOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public Contact setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Contact setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public Contact setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
		return this;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public Contact setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
		return this;
	}

	public String getBackground() {
		return background;
	}

	public Contact setBackground(String background) {
		this.background = background;
		return this;
	}

	public List<URL> getUrls() {
		return urls;
	}

	public Contact setUrls(List<URL> urls) {
		this.urls = urls;
		return this;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public Contact setPhones(List<Phone> phones) {
		this.phones = phones;
		return this;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public Contact setEmails(List<Email> emails) {
		this.emails = emails;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Contact setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getStatusId() {
		return statusId;
	}

	public Contact setStatusId(String statusId) {
		this.statusId = statusId;
		return this;
	}

	public boolean isStarred() {
		return starred;
	}

	public Contact setStarred(boolean starred) {
		this.starred = starred;
		return this;
	}

	public String getLeadSourceId() {
		return leadSourceId;
	}

	public Contact setLeadSourceId(String leadSourceId) {
		this.leadSourceId = leadSourceId;
		return this;
	}

	public boolean isPendingDeal() {
		return pendingDeal;
	}

	public Contact setPendingDeal(boolean pendingDeal) {
		this.pendingDeal = pendingDeal;
		return this;
	}

	public String getType() {
		return type;
	}

	public Contact setType(String type) {
		this.type = type;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Contact setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getCompanyId() {
		return companyId;
	}

	public Contact setCompanyId(String companyId) {
		this.companyId = companyId;
		return this;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public Contact setTags(List<Tag> tags) {
		this.tags = tags;
		return this;
	}

	public List<CustomField> getCustomFields() {
		return customFields;
	}

	public Contact setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
		return this;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public Contact setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public Contact setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
		return this;
	}

	public List<String> getAddressLines() {
		return addressLines;
	}

	public Contact setAddressLines(List<String> addressLines) {
		this.addressLines = addressLines;
		return this;
	}

	@Override
	public String toString() {

		String retString = "Contact{" + "id=\'" + id + "\', ownerId=\'" + ownerId + "\'" + ", firstName=\'" + firstName + "\'"
				+ ", lastName=\'" + lastName + "\'" + ", companyName=\'" + companyName + "\'";

		if (phones != null && !phones.isEmpty()) {
			retString += ", Phones{";
			for (int i = 0; i < phones.size(); i++) {
				if (i == (phones.size() - 1)) {
					retString += phones.get(i).getType() + "=\'" + phones.get(i).getNumber() + "\'";
				} else {
					retString += phones.get(i).getType() + "=\'" + phones.get(i).getNumber() + "\', ";
				}
			}
			retString += "}";
		}
		retString += "}";

		return retString;
	}
}
