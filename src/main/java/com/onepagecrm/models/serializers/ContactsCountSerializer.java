package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.ContactsCount;
import com.onepagecrm.models.internal.CountMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 13/04/2016.
 */
public class ContactsCountSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactsCountSerializer.class.getName());

    public static ContactsCount fromJsonObject(JSONObject contactsCountObject) {
        ContactsCount contactsCount = new ContactsCount();
        Map<String, CountMap> counts = new HashMap<>();
        try {
            if (contactsCountObject.has(ALL_TAG)) {
                JSONObject allObject = contactsCountObject.getJSONObject(ALL_TAG);
                CountMap accountTotals = CountMapSerializer.fromJsonObject(allObject);
                accountTotals.setAccount(true);
                counts.put(null, accountTotals);
            }
            if (contactsCountObject.has(USERS_TAG)) {
                JSONArray usersArray = contactsCountObject.getJSONArray(USERS_TAG);
                for (int i = 0; i < usersArray.length(); i++) {
                    JSONObject userObject = usersArray.getJSONObject(i);
                    CountMap userCounts = CountMapSerializer.fromJsonObject(userObject);
                    userCounts.setAccount(false);
                    counts.put(userCounts.getUserId(), userCounts);
                }
            }
            return contactsCount.setCounts(counts);

        } catch (JSONException e) {
            LOG.severe("Error parsing ContactsCount object");
            LOG.severe(e.toString());
        }
        return contactsCount;
    }
}
