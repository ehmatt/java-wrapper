package com.onepagecrm.api.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Contact implements Serializable {

    private String id;
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
//    private LeadSource leadSource;

    private boolean pendingDeal;
    private String type;
    private String companyName;
    private String companyId;
    
    // "sale_slosed_for": [ ]
    
    private List<Tag> tags;
    private List<CustomField> customFields;
    private String createdAt;
    private String modifiedAt;
    
    private List<String> addressLines;
//    private Address address;
    

//    private ArrayList<Phone> phones;

    private int notificationId;
    public static int nextNotificationId = 1;

    /**
     * Constructor for Contact object.
     *
     * @param id
     * @param ownerId
     * @param firstName
     * @param lastName
     * @param phones
     * @param companyName
     */
    public Contact(String id, String ownerId, String firstName, String lastName,
                   ArrayList<Phone> phones, String companyName) {
        this.setId(id);
        this.setOwnerId(ownerId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhones(phones);
        this.setCompanyName(companyName);
        if (phones != null && !phones.isEmpty()) {
            this.notificationId = nextNotificationId;
            nextNotificationId++;
        }
    }

    public Contact() {}

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {

        String retString = "Contact{" +
                "id=" + id +
                ", ownerId='" + ownerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'';

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
