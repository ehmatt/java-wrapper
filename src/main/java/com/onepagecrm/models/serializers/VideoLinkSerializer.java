package com.onepagecrm.models.serializers;

import com.onepagecrm.models.VideoLink;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VideoLinkSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(VideoLinkSerializer.class.getName());

    private static VideoLink DEFAULT = new VideoLink();
    private static List<VideoLink> DEFAULT_LIST = new ArrayList<>();

    public static VideoLink fromJsonObject(JSONObject videoLinkObject) {
        if (videoLinkObject == null) {
            return DEFAULT;
        }
        return new VideoLink()
                .setLink(videoLinkObject.optString(LINK_TAG))
                .setResolution(videoLinkObject.optString(RESOLUTION_TAG));
    }

    public static List<VideoLink> fromJsonArray(JSONArray videoLinkArray) {
        List<VideoLink> videoLinks = new ArrayList<>();
        if (videoLinkArray == null) return videoLinks;
        for (int i = 0; i < videoLinkArray.length(); i++) {
            JSONObject videoLinkObject = videoLinkArray.optJSONObject(i);
            videoLinks.add(fromJsonObject(videoLinkObject));
        }
        return videoLinks;
    }

    public static JSONObject toJsonObject(VideoLink videoLink) {
        JSONObject videoLinkObject = new JSONObject();
        if (videoLink == null) return videoLinkObject;
        addJsonStringValue(videoLink.getLink(), videoLinkObject, LINK_TAG);
        addJsonStringValue(videoLink.getResolution(), videoLinkObject, RESOLUTION_TAG);
        return videoLinkObject;
    }

    public static JSONArray toJsonArray(List<VideoLink> videoLinks) {
        JSONArray videoLinkArray = new JSONArray();
        if (videoLinks == null) return videoLinkArray;
        for (VideoLink videoLink : videoLinks) {
            videoLinkArray.put(toJsonObject(videoLink));
        }
        return videoLinkArray;
    }

    public static String toJsonString(VideoLink videoLink) {
        return toJsonObject(videoLink).toString();
    }

    public static String toJsonString(List<VideoLink> videoLinks) {
        return toJsonArray(videoLinks).toString();
    }
}
