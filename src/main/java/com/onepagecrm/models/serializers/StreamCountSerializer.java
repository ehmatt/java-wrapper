package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.internal.StreamCount;
import com.onepagecrm.models.internal.TeamCount;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 13/04/2016.
 */
@SuppressWarnings("unused")
public class StreamCountSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(StreamCountSerializer.class.getName());

    public static StreamCount fromJsonObject(JSONObject streamCountObject) {
        StreamCount streamCount = new StreamCount();
        Map<String, TeamCount> counts = new HashMap<>();

        if (streamCountObject == null) {
            return streamCount.setCounts(counts);
        }

        TeamCount accountTotals = new TeamCount()
                .setUserId(Account.USER_ID)
                .setCount(streamCountObject.optInt(ALL_TAG));
        counts.put(Account.USER_ID, accountTotals);

        JSONArray usersArray = streamCountObject.optJSONArray(USERS_TAG);
        if (usersArray == null) usersArray = new JSONArray();
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userObject = usersArray.optJSONObject(i);
            TeamCount count = TeamCountsSerializer.fromJsonObject(userObject);
            counts.put(count.getUserId(), count);
        }

        return streamCount.setCounts(counts);
    }
}
