package com.onepagecrm.api.models;

import java.io.Serializable;
import java.util.List;

import com.onepagecrm.api.net.ApiResource;


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
	private List<URL> photoUrls;
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
	public Contact(
			String id, String ownerId, String firstName, String lastName, String photoUrl, String jobTitle,
			String background, List<URL> photoUrls, List<Phone> phones, List<Email> emails, String status,
			String statusId, boolean starred, String leadSourceId, boolean pendingDeal, String type, 
			String companyName, String companyId, List<Tag> tags, List<CustomField> customFields, 
			String createdAt, String modifiedAt, List<String> addressLines, int notificationId) {

		this.id = id;
		this.intId = nextIntId;
		nextIntId++;
		
		this.ownerId = ownerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photoUrl = photoUrl;
		this.jobTitle = jobTitle;
		this.background = background;
		this.photoUrls = photoUrls;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getIntId() {
		return intId;
	}

	public void setIntId(int intId) {
		this.intId = intId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public List<URL> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<URL> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public boolean isStarred() {
		return starred;
	}

	public void setStarred(boolean starred) {
		this.starred = starred;
	}

	public String getLeadSourceId() {
		return leadSourceId;
	}

	public void setLeadSourceId(String leadSourceId) {
		this.leadSourceId = leadSourceId;
	}

	public boolean isPendingDeal() {
		return pendingDeal;
	}

	public void setPendingDeal(boolean pendingDeal) {
		this.pendingDeal = pendingDeal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<CustomField> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public List<String> getAddressLines() {
		return addressLines;
	}

	public void setAddressLines(List<String> addressLines) {
		this.addressLines = addressLines;
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
