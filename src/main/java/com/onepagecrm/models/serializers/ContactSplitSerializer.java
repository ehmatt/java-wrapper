package com.onepagecrm.models.serializers;

import org.json.JSONObject;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2017.
 */
public class ContactSplitSerializer extends BaseSerializer {

    public static String toJsonObject(String newCompanyName) {
        JSONObject splitObject = new JSONObject();
        addJsonStringValue(newCompanyName, splitObject, COMPANY_NAME_TAG);
        return splitObject.toString();
    }
}
