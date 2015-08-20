package com.onepagecrm.models;

import java.util.List;
import java.io.Serializable;

import com.onepagecrm.models.internal.Sales;
import com.onepagecrm.models.serializer.ContactSerializer;
import com.onepagecrm.models.serializer.LoginSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.LoginRequest;
import com.onepagecrm.net.request.Request;

public class User extends ApiResource implements Serializable {

    private static final long serialVersionUID = 1383622287570201668L;
    
    private static final String ACTION_STREAM_ENDPOINT = "action_stream";
    private static final String CONTACTS_ENDPOINT = "contacts";

    private String id;
    private String authKey;
    private String accountType;
    private String bccEmail;
    private String companyName;
    private String email;
    private String firstName;
    private String lastName;
    private String photoUrl;

    private Sales sales;
    private Account account;

    private List<CallResult> callResults;

    public static User login(String username, String password) {
	Request request = new LoginRequest(username, password);
	Response response = request.send();
	return LoginSerializer.parseLogin(response.getResponseBody());
    }

    public ContactList actionStream() {
	Request request = new GetRequest(ACTION_STREAM_ENDPOINT, perPageQueryString(100), null);
	Response response = request.send();
	return ContactSerializer.fromString(response.getResponseBody());
    }

    public ContactList contacts() {
	Request request = new GetRequest(CONTACTS_ENDPOINT, perPageQueryString(100), null);
	Response response = request.send();
	return ContactSerializer.fromString(response.getResponseBody());
    }
    
    private String perPageQueryString(int number) {
	return "?per_page=" + number; 
    }

    public User() {

    }

    public boolean isValid() {
	return id != null && !id.equals("");
    }

    public String getId() {
	return id;
    }

    public User setId(String id) {
	this.id = id;
	return this;
    }

    public String getAuthKey() {
	return authKey;
    }

    public User setAuthKey(String authKey) {
	this.authKey = authKey;
	return this;
    }

    public String getAccountType() {
	return accountType;
    }

    public User setAccountType(String accountType) {
	this.accountType = accountType;
	return this;
    }

    public String getBccEmail() {
	return bccEmail;
    }

    public User setBccEmail(String bccEmail) {
	this.bccEmail = bccEmail;
	return this;
    }

    public String getCompanyName() {
	return companyName;
    }

    public User setCompanyName(String companyName) {
	this.companyName = companyName;
	return this;
    }

    public String getEmail() {
	return email;
    }

    public User setEmail(String email) {
	this.email = email;
	return this;
    }

    public String getFirstName() {
	return firstName;
    }

    public User setFirstName(String firstName) {
	this.firstName = firstName;
	return this;
    }

    public String getLastName() {
	return lastName;
    }

    public User setLastName(String lastName) {
	this.lastName = lastName;
	return this;
    }

    public String getPhotoUrl() {
	return photoUrl;
    }

    public User setPhotoUrl(String photoUrl) {
	this.photoUrl = photoUrl;
	return this;
    }

    public Sales getSales() {
	return sales;
    }

    public User setSales(Sales sales) {
	this.sales = sales;
	return this;
    }

    public Account getAccount() {
	return account;
    }

    public User setAccount(Account account) {
	this.account = account;
	return this;
    }

    public List<CallResult> getCallResults() {
	return callResults;
    }

    public User setCallResults(List<CallResult> callResults) {
	this.callResults = callResults;
	return this;
    }

    @Override
    public String toString() {
	return "User{" + "id=\'" + id + "\', authKey=\'" + authKey + "\'" + ", accountType=\'"
		+ accountType + "\'" + ", firstName=\'" + firstName + "\'" + ", lastName=\'"
		+ lastName + "\'" + ", email=\'" + email + "\'" + ", companyName=\'" + companyName
		+ "\'" + ", photoUrl=\'" + photoUrl + "\'" + ", bccEmail=\'" + bccEmail + "\'";
    }
}