package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializers.CompanySerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.PutRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/12/2016.
 */
public class Company extends ApiResource implements Serializable {

    private String id;
    private String name;
    private String description;
    private String phone;
    private String url;
    private List<CustomField> companyFields;
    private String syncedStatusId;
    private Address address;
    private Integer wonDealsCount;
    private Double totalWonAmount;
    private Integer pendingDealsCount;
    private Double totalPendingAmount;
    private Integer contactsCount;
    private ContactList contacts;
    private DealList pendingDeals;
    private Date createdAt;

    public Company save() throws OnePageException {
        return this.isValid() ? update() : create();
    }

    private Company create() throws OnePageException {
        Request request = new PostRequest(
                COMPANIES_ENDPOINT,
                null,
                CompanySerializer.toJsonObject(this)
        );
        Response response = request.send();
        String responseBody = response.getResponseBody();
        return CompanySerializer.fromString(responseBody);
    }

    private Company update() throws OnePageException {
        Request request = new PutRequest(
                addIdToEndpoint(COMPANIES_ENDPOINT, this.id),
                null,
                CompanySerializer.toJsonObject(this)
        );
        Response response = request.send();
        String responseBody = response.getResponseBody();
        return CompanySerializer.fromString(responseBody);
    }

    public static Company getSingleCompany(String companyId) throws OnePageException {
        Request request = new GetRequest(
                addIdToEndpoint(COMPANIES_ENDPOINT, companyId),
                null
        );
        Response response = request.send();
        String responseBody = response.getResponseBody();
        return CompanySerializer.fromString(responseBody);
    }

    public Company partialUpdate(Company updateValues) throws OnePageException {
        Map<String, Object> params = new HashMap<>();
        params.put("partial", true);
        Request request = new PutRequest(
                addIdToEndpoint(COMPANIES_ENDPOINT, this.id),
                Query.fromParams(params),
                CompanySerializer.toJsonObject(updateValues)
        );
        Response response = request.send();
        String responseBody = response.getResponseBody();
        return CompanySerializer.fromString(responseBody);
    }

    private static String addIdToEndpoint(String endpoint, String id) {
        return endpoint + "/" + id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Company setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return CompanySerializer.toJsonObject(this);
    }

    public boolean dataToLoseWithDeletion() {
        return isValid() && (notNullOrEmpty(description) ||
                notNullOrEmpty(phone) ||
                notNullOrEmpty(url) ||
                companyFields != null && !companyFields.isEmpty() ||
                address != null && address.isValid());
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Company setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Company setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Company setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<CustomField> getCompanyFields() {
        return companyFields;
    }

    public Company setCompanyFields(List<CustomField> companyFields) {
        this.companyFields = companyFields;
        return this;
    }

    public String getSyncedStatusId() {
        return syncedStatusId;
    }

    public Company setSyncedStatusId(String syncedStatusId) {
        this.syncedStatusId = syncedStatusId;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Company setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Integer getWonDealsCount() {
        return wonDealsCount;
    }

    public Company setWonDealsCount(Integer wonDealsCount) {
        this.wonDealsCount = wonDealsCount;
        return this;
    }

    public Double getTotalWonAmount() {
        return totalWonAmount;
    }

    public Company setTotalWonAmount(Double totalWonAmount) {
        this.totalWonAmount = totalWonAmount;
        return this;
    }

    public Integer getPendingDealsCount() {
        return pendingDealsCount;
    }

    public Company setPendingDealsCount(Integer pendingDealsCount) {
        this.pendingDealsCount = pendingDealsCount;
        return this;
    }

    public Double getTotalPendingAmount() {
        return totalPendingAmount;
    }

    public Company setTotalPendingAmount(Double totalPendingAmount) {
        this.totalPendingAmount = totalPendingAmount;
        return this;
    }

    public Integer getContactsCount() {
        return contactsCount;
    }

    public Company setContactsCount(Integer contactsCount) {
        this.contactsCount = contactsCount;
        return this;
    }

    public ContactList getContacts() {
        return contacts;
    }

    public Company setContacts(ContactList contacts) {
        this.contacts = contacts;
        return this;
    }

    public DealList getPendingDeals() {
        return pendingDeals;
    }

    public Company setPendingDeals(DealList pendingDeals) {
        this.pendingDeals = pendingDeals;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Company setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
