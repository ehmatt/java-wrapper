package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Company;
import com.onepagecrm.models.CustomField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/12/2016.
 */
public class CompanySerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CompanySerializer.class.getName());

    public static Company fromString(String responseBody) throws OnePageException {
        Company company = new Company();
        try {
            String parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            return fromJsonObject(responseObject);

        } catch (ClassCastException e) {
            throw (OnePageException) BaseSerializer.fromString(responseBody);

        } catch (JSONException e) {
            LOG.severe("Error parsing Company from JSON.");
            LOG.severe(e.toString());
        }
        return company;
    }

    public static Company fromJsonObject(JSONObject companyObject) {
        // Fix for some objects not having name.
        if (companyObject.has(COMPANY_TAG)) {
            companyObject = companyObject.optJSONObject(COMPANY_TAG);
        }
        return new Company()
                .setId(companyObject.optString(ID_TAG))
                .setName(companyObject.optString(NAME_TAG))
                .setDescription(companyObject.optString(DESCRIPTION_TAG))
                .setPhone(companyObject.optString(PHONE_TAG))
                .setUrl(companyObject.optString(URL_TAG))
                .setCompanyFields(CustomFieldSerializer.fromJsonArray(companyObject.optJSONArray(COMPANY_FIELDS_TAG),
                        CustomField.CF_TYPE_COMPANY))
                .setSyncedStatusId(companyObject.optString(SYNCED_STATUS_ID_TAG))
                .setAddress(AddressSerializer.fromJsonObject(companyObject.optJSONObject(ADDRESS_TAG)))
                .setWonDealsCount(companyObject.optInt(WON_DEALS_COUNT_TAG))
                .setTotalWonAmount(companyObject.optDouble(TOTAL_WON_AMOUNT_TAG, 0d))
                .setPendingDealsCount(companyObject.optInt(PENDING_DEALS_COUNT_TAG))
                .setTotalPendingAmount(companyObject.optDouble(TOTAL_PENDING_AMOUNT_TAG, 0d))
                .setContactsCount(companyObject.optInt(CONTACTS_COUNT_TAG))
                //.setContacts(ContactListSerializer.fromJsonObject(companyObject))
                ;
    }

    public static List<Company> fromJsonArray(JSONArray companiesArray) {
        List<Company> companies = new ArrayList<>();
        for (int i = 0; i < companiesArray.length(); ++i) {
            JSONObject companyObject = companiesArray.optJSONObject(i);
            Company company = fromJsonObject(companyObject);
            if (company != null) {
                companies.add(company);
            }
        }
        return companies;
    }

    public static String toJsonObject(Company company) {
        JSONObject companyObject = new JSONObject();
        if (company != null) {
            addJsonStringValue(company.getId(), companyObject, ID_TAG);
            addJsonStringValue(company.getName(), companyObject, NAME_TAG);
            addJsonStringValue(company.getDescription(), companyObject, DESCRIPTION_TAG);
            addJsonStringValue(company.getPhone(), companyObject, PHONE_TAG);
            addJsonStringValue(company.getUrl(), companyObject, URL_TAG);
            addJsonStringValue(CustomFieldSerializer.toJsonArray(company.getCompanyFields()), companyObject, COMPANY_FIELDS_TAG);
            addJsonStringValue(company.getSyncedStatusId(), companyObject, SYNCED_STATUS_ID_TAG);
            try {
                JSONObject addressArray = new JSONObject(AddressSerializer.toJsonObject(company.getAddress()));
                addJsonObject(addressArray, companyObject, ADDRESS_TAG);
            } catch (JSONException e) {
                LOG.severe("Error creating Address object while constructing Company object");
                LOG.severe(e.toString());
            }
            addJsonIntegerValue(company.getWonDealsCount(), companyObject, WON_DEALS_COUNT_TAG);
            addJsonDoubleValue(company.getTotalWonAmount(), companyObject, TOTAL_WON_AMOUNT_TAG);
            addJsonIntegerValue(company.getPendingDealsCount(), companyObject, PENDING_DEALS_COUNT_TAG);
            addJsonDoubleValue(company.getTotalPendingAmount(), companyObject, TOTAL_PENDING_AMOUNT_TAG);
            addJsonIntegerValue(company.getContactsCount(), companyObject, CONTACTS_COUNT_TAG);
            try {
                JSONArray contactsArray = new JSONArray(ContactSerializer.toJsonArray(company.getContacts()));
                addJsonArray(contactsArray, companyObject, CONTACTS_TAG);
            } catch (JSONException e) {
                LOG.severe("Problems generating JSONArray of contacts for this company.");
                LOG.severe(e.toString());
            }
        }
        return companyObject.toString();
    }

    public static String toJsonArray(List<Company> companies) {
        JSONArray companiesArray = new JSONArray();
        for (int i = 0; i < companies.size(); i++) {
            try {
                companiesArray.put(new JSONObject(toJsonObject(companies.get(i))));
            } catch (JSONException e) {
                LOG.severe("Error creating JSON out of Company(s).");
                LOG.severe(e.toString());
            }
        }
        return companiesArray.toString();
    }
}
