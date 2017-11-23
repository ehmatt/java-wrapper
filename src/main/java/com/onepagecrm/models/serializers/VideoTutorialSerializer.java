package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.VideoTutorial;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VideoTutorialSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(VideoTutorialSerializer.class.getName());

    private static VideoTutorial DEFAULT = new VideoTutorial();
    private static List<VideoTutorial> DEFAULT_LIST = new ArrayList<>();

    public static List<VideoTutorial> fromResponse(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        JSONArray videoTutorialArray = dataObject.optJSONArray(VIDEO_TUTORIALS_TAG);
        return fromJsonArray(videoTutorialArray);
    }

    // TODO: delete
    public static List<VideoTutorial> fromString(String responseBody) throws APIException {
        try {
            String dataString = (String) BaseSerializer.fromString(responseBody);
            JSONObject dataObject = new JSONObject(dataString);
            JSONArray videoTutorialsArray = dataObject.optJSONArray(VIDEO_TUTORIALS_TAG);
            return fromJsonArray(videoTutorialsArray);

        } catch (JSONException e) {
            LOG.severe("Could not parse Video Tutorials");
            LOG.severe(e.toString());
            return DEFAULT_LIST;
        }
    }

    public static List<VideoTutorial> fromJsonArray(JSONArray videoTutorialArray) {
        List<VideoTutorial> videoTutorials = new ArrayList<>();
        if (videoTutorialArray == null) return videoTutorials;
        for (int i = 0; i < videoTutorialArray.length(); i++) {
            JSONObject videoTutorialObject = videoTutorialArray.optJSONObject(i);
            VideoTutorial videoTutorial = fromJsonObject(videoTutorialObject);
            videoTutorial.setId(String.valueOf(i));
            videoTutorials.add(videoTutorial);
        }
        return videoTutorials;
    }

    public static VideoTutorial fromJsonObject(JSONObject videoTutorialObject) {
        if (videoTutorialObject == null) {
            return DEFAULT;
        }
        return new VideoTutorial()
                .setName(videoTutorialObject.optString(VIDEO_NAME_TAG))
                .setVideoLinks(VideoLinkSerializer.fromJsonArray(videoTutorialObject.optJSONArray(LINKS_TAG)));
    }

    public static JSONObject toJsonObject(VideoTutorial videoTutorial) {
        JSONObject videoTutorialObject = new JSONObject();
        if (videoTutorial == null) return videoTutorialObject;
        addJsonStringValue(videoTutorial.getName(), videoTutorialObject, VIDEO_NAME_TAG);
        JSONArray videoLinksArray = VideoLinkSerializer.toJsonArray(videoTutorial.getVideoLinks());
        addJsonArray(videoLinksArray, videoTutorialObject, LINKS_TAG);
        return videoTutorialObject;
    }

    public static String toJsonString(VideoTutorial videoTutorial) {
        return toJsonObject(videoTutorial).toString();
    }

    public static JSONArray toJsonArray(List<VideoTutorial> videoTutorials) {
        JSONArray videoTutorialsArray = new JSONArray();
        if (videoTutorials == null) return videoTutorialsArray;
        for (VideoTutorial videoTutorial : videoTutorials) {
            videoTutorialsArray.put(toJsonObject(videoTutorial));
        }
        return videoTutorialsArray;
    }

    public static String toJsonString(List<VideoTutorial> videoTutorials) {
        return toJsonArray(videoTutorials).toString();
    }
}
