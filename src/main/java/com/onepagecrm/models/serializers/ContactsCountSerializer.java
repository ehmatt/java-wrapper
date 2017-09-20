package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.internal.ContactsCount;
import com.onepagecrm.models.internal.CountMap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 13/04/2016.
 */
@SuppressWarnings("unused")
public class ContactsCountSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactsCountSerializer.class.getName());

    public static ContactsCount fromJsonObject(JSONObject contactsCountObject) {
        ContactsCount contactsCount = new ContactsCount();
        Map<String, CountMap> counts = new HashMap<>();

        if (contactsCountObject == null) {
            return contactsCount.setCounts(counts);
        }

        JSONObject allObject = contactsCountObject.optJSONObject(ALL_TAG);
        CountMap accountTotals = CountMapSerializer.fromJsonObject(allObject);
        counts.put(Account.USER_ID, accountTotals.setUserId(Account.USER_ID));

        JSONArray usersArray = contactsCountObject.optJSONArray(USERS_TAG);
        if (usersArray == null) usersArray = new JSONArray();
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userObject = usersArray.optJSONObject(i);
            CountMap userCounts = CountMapSerializer.fromJsonObject(userObject);
            counts.put(userCounts.getUserId(), userCounts);
        }

        return contactsCount.setCounts(counts);
    }
}
