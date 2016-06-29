package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.StreamCount;
import com.onepagecrm.models.internal.TeamCount;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 13/04/2016.
 */
public class StreamCountSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(StreamCountSerializer.class.getName());

    public static StreamCount fromJsonObject(JSONObject streamCountObject) {
        StreamCount streamCount = new StreamCount();
        Map<String, TeamCount> userCounts = new HashMap<>();
        try {
            if (streamCountObject.has(ALL_TAG)) {
                streamCount.setAll(streamCountObject.getInt(ALL_TAG));
            }
            if (streamCountObject.has(USERS_TAG)) {
                JSONArray usersArray = streamCountObject.getJSONArray(USERS_TAG);
                for (int i = 0; i < usersArray.length(); i++) {
                    JSONObject userObject = usersArray.getJSONObject(i);
                    TeamCount count = TeamCountsSerializer.fromJsonObject(userObject);
                    userCounts.put(count.getUserId(), count);
                }
            }
            return streamCount.setUsers(userCounts);

        } catch (JSONException e) {
            LOG.severe("Error parsing StreamCount object");
            LOG.severe(e.toString());
        }
        return streamCount;
    }
}
