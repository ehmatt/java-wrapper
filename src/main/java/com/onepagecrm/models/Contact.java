package com.onepagecrm.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.onepagecrm.net.ApiResource;


public class Contact extends ApiResource implements Serializable {

	private static final long serialVersionUID = -6073805195226829625L;

	private String id;

	private int intId;
	private static int nextIntId = 0;

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

	/**
	 * Constructor which accepts every attribute.
	 * 
	 * @param id
	 * @param ownerId
	 * @param firstName
	 * @param lastName
	 * @param photoUrl
	 * @param jobTitle
	 * @param background
	 * @param photoUrls
	 * @param phones
	 * @param emails
	 * @param status
	 * @param statusId
	 * @param starred
	 * @param leadSourceId
	 * @param pendingDeal
	 * @param type
	 * @param companyName
	 * @param companyId
	 * @param tags
	 * @param customFields
	 * @param createdAt
	 * @param modifiedAt
	 * @param addressLines
	 * @param notificationId
	 */
	public Contact(String id, String ownerId, String firstName, String lastName, String photoUrl, String jobTitle,
			String background, List<URL> urls, List<Phone> phones, List<Email> emails, String status,
			String statusId, boolean starred, String leadSourceId, boolean pendingDeal, String type, String companyName,
			String companyId, List<Tag> tags, List<CustomField> customFields, String createdAt, String modifiedAt,
			List<String> addressLines, int notificationId) {

		this.id = id;
		this.intId = nextIntId;
		nextIntId++;

		this.ownerId = ownerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photoUrl = photoUrl;
		this.jobTitle = jobTitle;
		this.background = background;
		this.urls = urls;
		this.phones = phones;
		this.emails = emails;
		this.status = status;
		this.statusId = statusId;
		this.starred = starred;
		this.leadSourceId = leadSourceId;
		this.pendingDeal = pendingDeal;
		this.type = type;
		this.companyName = companyName;
		this.companyId = companyId;
		this.tags = tags;
		this.customFields = customFields;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.addressLines = addressLines;
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

		String retString = "Contact{" + "id=" + id + ", ownerId='" + ownerId + '\'' + ", firstName='" + firstName + '\''
				+ ", lastName='" + lastName + '\'' + ", companyName='" + companyName + '\'';

		if (phones != null && !phones.isEmpty()) {
			retString += ", phones{";
			for (int i = 0; i < phones.size(); i++) {
				if (i == (phones.size() - 1)) {
					retString += phones.get(i).getType() + "='" + phones.get(i).getNumber() + "'";
				} else {
					retString += phones.get(i).getType() + "='" + phones.get(i).getNumber() + "', ";
				}
			}
			retString += "}";
		}
		retString += "}";

		return retString;
	}
}
