package com.onepagecrm.api.models;

import java.io.Serializable;
import java.util.ArrayList;


public class Contact implements Serializable {

    private String id;
    private String ownerId;
    private String firstName;
    private String lastName;
    private String companyName;

    private ArrayList<Phone> phones;

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

    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<Phone> phones) {
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
