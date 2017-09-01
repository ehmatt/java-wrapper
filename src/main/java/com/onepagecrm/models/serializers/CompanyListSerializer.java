package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.CompanyList;
import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/12/2016.
 */
public class CompanyListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CompanyListSerializer.class.getName());

    public static CompanyList fromResponse(Response response) throws APIException {
        final String responseBody = response.getResponseBody();
        JSONObject dataObject = (JSONObject) BaseSerializer.fromString(responseBody);
        JSONArray companiesArray = dataObject.optJSONArray(COMPANIES_TAG);
        Paginator paginator = RequestMetadataSerializer.fromJsonObject(dataObject);
        CompanyList companies = new CompanyList();
        companies.setPaginator(paginator);
        companies.setList(CompanySerializer.fromJsonArray(companiesArray));
        return companies;
    }

    // TODO remove
    public static CompanyList fromString(String responseBody) throws APIException {
        CompanyList companies = new CompanyList();

        try {
            String dataString = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(dataString);
            JSONArray companiesArray = responseObject.optJSONArray(COMPANIES_TAG);
            Paginator paginator = RequestMetadataSerializer.fromJsonObject(responseObject);
            companies.setPaginator(paginator);
            companies.setList(CompanySerializer.fromJsonArray(companiesArray));

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
