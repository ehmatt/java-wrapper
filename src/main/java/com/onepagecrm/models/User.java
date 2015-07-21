package com.onepagecrm.models;

import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable {

    private String id;
    private String authKey;
    private String accountType;
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private String photoUrl;
    private String bccEmail;

    private ArrayList<CallResult> callResults;

    /**
     * Constructor for User object.
     *
     * @param id
     * @param authKey
     * @param accountType
     * @param firstName
     * @param lastName
     * @param email
     * @param company
     * @param photoUrl
     * @param bccEmail
     */
    public User(String id, String authKey, String accountType, String firstName,
                String lastName, String email, String company, String photoUrl,
                String bccEmail, ArrayList<CallResult> callResults) {

        this.setId(id);
        this.setAuthKey(authKey);
        this.setAccountType(accountType);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setCompany(company);
        this.setPhotoUrl(photoUrl);
        this.setBccEmail(bccEmail);
        this.setCallResults(callResults);
    }

    public User() {}
    
    public boolean isValid() {
    	return id != null && !id.equals("");
    }

    public ArrayList<CallResult> getCallResults() {
        return callResults;
    }

    public void setCallResults(ArrayList<CallResult> callResults) {
        this.callResults = callResults;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getBccEmail() {
        return bccEmail;
    }

    public void setBccEmail(String bccEmail) {
        this.bccEmail = bccEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", authKey='" + authKey + '\'' +
                ", accountType='" + accountType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", bccEmail='" + bccEmail + '\'' +
                ", callRsults='" + callResults + '\'' +
                '}';
    }
}