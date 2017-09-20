package com.onepagecrm.models.serializers;

import org.json.JSONObject;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/02/2017.
 */
public class ContactSplitSerializer extends BaseSerializer {

    public static JSONObject toJsonObject(String newCompanyName) {
        JSONObject splitObject = new JSONObject();
        addJsonStringValue(newCompanyName, splitObject, COMPANY_NAME_TAG);
        return splitObject;
    }

    public static String toJsonString(String newCompanyName) {
        return toJsonObject(newCompanyName).toString();
    }
}
