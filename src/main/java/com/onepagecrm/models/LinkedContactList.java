package com.onepagecrm.models;

import com.onepagecrm.models.internal.Paginator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 12/10/2017.
 */
@SuppressWarnings({"WeakerAccess", "unused", "UnusedReturnValue"})
public class LinkedContactList implements Serializable {

    private List<LinkedContact> links;
    private Paginator paginator;
    private Map<String, Contact> contactMap;
    private Map<String, Company> companyMap;

    public LinkedContactList() {

    }

    /* Helper methods */

    public LinkedContact getLinkedContact(int position) {
        return links != null ? links.get(position) : null;
    }

    public Contact getContact(int position) {
        return contactMap != null ? contactMap.get(getContactId(position)) : null;
    }

    public Company getCompany(int position) {
        return companyMap != null ? companyMap.get(getCompanyId(position)) : null;
    }

    public String getLinkedWithId(int position) {
        return links != null ? links.get(position).getLinkedWithId() : null;
    }

    public String getContactId(int position) {
        return links != null ? links.get(position).getContactId() : null;
    }

    public String getCompanyId(int position) {
        return links != null ? links.get(position).getCompanyId() : null;
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        if (links == null) return contacts;
        for (int i = 0; i < links.size(); i++) {
            contacts.add(getContact(i));
        }
        return contacts;
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        if (links == null) return companies;
        for (int i = 0; i < links.size(); i++) {
            companies.add(getCompany(i));
        }
        return companies;
    }

    public int size() {
        return links != null ? links.size() : -1;
    }

    public LinkedContact get(int position) {
        return links != null ? links.get(position) : null;
    }

    /* Accessors */

    public List<LinkedContact> getLinks() {
        return links;
    }

    public LinkedContactList setLinks(List<LinkedContact> links) {
        this.links = links;
        return this;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public LinkedContactList setPaginator(Paginator paginator) {
        this.paginator = paginator;
        return this;
    }

    public Map<String, Contact> getContactMap() {
        return contactMap;
    }

    public LinkedContactList setContactMap(Map<String, Contact> contactMap) {
        this.contactMap = contactMap;
        return this;
    }

    public LinkedContactList setContactMap(ContactList contacts) {
        this.contactMap = new HashMap<>();
        if (contacts == null) return this;
        this.paginator = contacts.getPaginator();
        for (Contact contact : contacts) {
            this.contactMap.put(contact.getId(), contact);
        }
        return this;
    }

    public Map<String, Company> getCompanyMap() {
        return companyMap;
    }

    public LinkedContactList setCompanyMap(Map<String, Company> companyMap) {
        this.companyMap = companyMap;
        return this;
    }
}
