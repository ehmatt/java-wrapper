package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.LinkedContact;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/12/2016.
 */
public class LinkedContactSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(LinkedContactSerializer.class.getName());

    public static LinkedContact fromString(String responseBody) throws OnePageException {
        try {
            String parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            return fromJsonObject(responseObject);

        } catch (ClassCastException e) {
            throw (OnePageException) BaseSerializer.fromString(responseBody);

        } catch (JSONException e) {
            LOG.severe("Error parsing LinkedContact from JSON.");
            LOG.severe(e.toString());
        }

        return new LinkedContact();
    }

    public static LinkedContact fromJsonObject(JSONObject linkedContactObject) {
        LinkedContact linkedContact = new LinkedContact();
        String contactId = "";
        String companyId = "";
        String linkedWithId = "";

        try {
            if (linkedContactObject.has(LINKED_CONTACT_TAG)) {
                linkedContactObject = linkedContactObject.getJSONObject(LINKED_CONTACT_TAG);
            }
            if (linkedContactObject.has(CONTACT_TAG)) {
                JSONObject contactObject = linkedContactObject.getJSONObject(CONTACT_TAG);
                contactId = contactObject.optString(ID_TAG);
            }
            if (linkedContactObject.has(COMPANY_TAG)) {
                JSONObject companyObject = linkedContactObject.getJSONObject(COMPANY_TAG);
                companyId = companyObject.optString(ID_TAG);
            }
            if (linkedContactObject.has(LINKED_WITH_TAG)) {
                JSONArray linkedWithArray = linkedContactObject.getJSONArray(LINKED_WITH_TAG);
                linkedWithId = linkedWithArray.optString(0);
            }

        } catch (JSONException e) {
            LOG.severe("Error parsing LinkedContact object");
            LOG.severe(e.toString());
            return new LinkedContact();
        }

        return linkedContact
                .setContactId(contactId)
                .setCompanyId(companyId)
                .setLinkedWithId(linkedWithId);
    }
}
