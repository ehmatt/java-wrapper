package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Company;
import com.onepagecrm.models.CompanyList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/12/2016.
 */
public class CompanySerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactSerializer.class.getName());

    public static Company fromString(String responseBody) throws OnePageException {

    }

    public static Company fromJsonObject(JSONObject companyObject) {

    }

    public static CompanyList fromJsonArray(JSONArray companiesArray) {

    }

    public static String toJsonObject(Company company) {

    }

    public static String toJsonArray(CompanyList companies) {

    }
}
