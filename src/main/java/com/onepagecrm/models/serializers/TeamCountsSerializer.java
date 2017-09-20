package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.TeamCount;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 20/09/2016.
 */
@SuppressWarnings("unused")
public class TeamCountsSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(TeamCountsSerializer.class.getName());

    private static TeamCount DEFAULT = new TeamCount();

    public static TeamCount fromJsonObject(JSONObject teamCountObject) {
        if (teamCountObject == null) {
            return DEFAULT;
        }
        return new TeamCount()
                .setUserId(teamCountObject.optString(USER_ID_TAG))
                .setCount(teamCountObject.optInt(COUNTS_TAG));
    }

    public static List<TeamCount> fromJsonArray(JSONArray teamCountArray) {
        List<TeamCount> teamCounts = new ArrayList<>();
        if (teamCountArray == null) return teamCounts;
        for (int i = 0; i < teamCountArray.length(); i++) {
            JSONObject teamCountsObject = teamCountArray.optJSONObject(i);
            teamCounts.add(fromJsonObject(teamCountsObject));
        }
        return teamCounts;
    }

    public static JSONObject toJsonObject(TeamCount teamCount) {
        JSONObject teamCountObject = new JSONObject();
        if (teamCount == null) return teamCountObject;
        addJsonStringValue(teamCount.getUserId(), teamCountObject, USER_ID_TAG);
        addJsonIntValue(teamCount.getCount(), teamCountObject, COUNTS_TAG);
        return teamCountObject;
    }

    public static JSONArray toJsonArray(List<TeamCount> teamCounts) {
        JSONArray teamCountsArray = new JSONArray();
        if (teamCounts == null) return teamCountsArray;
        for (TeamCount teamCount : teamCounts) {
            teamCountsArray.put(toJsonObject(teamCount));
        }
        return teamCountsArray;
    }

    public static String toJsonString(TeamCount teamCount) {
        return toJsonObject(teamCount).toString();
    }

    public static String toJsonString(List<TeamCount> teamCounts) {
        return toJsonArray(teamCounts).toString();
    }
}
