package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Url;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 23/11/2017.
 */
public class UrlSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(UrlSerializer.class.getName());

    private static Url DEFAULT = new Url();

    public static Url fromJsonObject(JSONObject urlObject) {
        if (urlObject == null) {
            return DEFAULT;
        }
        if (urlObject.has(URL_TAG)) {
            urlObject = urlObject.optJSONObject(URL_TAG);
        }
        return new Url()
                .setType(urlObject.optString(TYPE_TAG))
                .setValue(urlObject.optString(VALUE_TAG));
    }

    public static List<Url> fromJsonArray(JSONArray urlsArray) {
        List<Url> urls = new ArrayList<>();
        if (urlsArray == null) return urls;
        for (int i = 0; i < urlsArray.length(); i++) {
            JSONObject urlObject = urlsArray.optJSONObject(i);
            urls.add(fromJsonObject(urlObject));
        }
        return urls;
    }

    public static JSONObject toJsonObject(Url url) {
        JSONObject urlObject = new JSONObject();
        if (url == null) return urlObject;
        addJsonStringValue(url.getType().toLowerCase(), urlObject, TYPE_TAG);
        addJsonStringValue(url.getValue(), urlObject, VALUE_TAG);
        return urlObject;
    }

    public static JSONArray toJsonArray(List<Url> urls) {
        JSONArray urlsArray = new JSONArray();
        if (urls == null) return urlsArray;
        for (Url url : urls) {
            urlsArray.put(toJsonObject(url));
        }
        return urlsArray;
    }

    public static String toJsonString(Url url) {
        return toJsonObject(url).toString();
    }

    public static String toJsonString(List<Url> urls) {
        return toJsonArray(urls).toString();
    }
}
