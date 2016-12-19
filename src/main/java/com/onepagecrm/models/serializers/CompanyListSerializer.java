package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.CompanyList;
import com.onepagecrm.models.internal.Paginator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/12/2016.
 */
public class CompanyListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CompanyListSerializer.class.getName());

    public static CompanyList fromString(String responseBody) throws OnePageException {
        CompanyList companies = new CompanyList();

        try {
            String parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            JSONArray companiesArray = responseObject.optJSONArray(COMPANIES_TAG);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(responseObject);
            companies.setPaginator(paginator);
            companies.setList(CompanySerializer.fromJsonArray(companiesArray));

        } catch (ClassCastException e) {
            throw (OnePageException) BaseSerializer.fromString(responseBody);

        } catch (Exception e) {
            LOG.severe("Error parsing CompanyList from JSON.");
            LOG.severe(e.toString());
        }

        return companies;
    }

    public static CompanyList fromJsonArray(JSONArray companiesArray) {
        return new CompanyList(CompanySerializer.fromJsonArray(companiesArray));
    }

    public static String toJsonArray(CompanyList companies) {
        return CompanySerializer.toJsonArray(new CompanyList(companies));
    }
}
