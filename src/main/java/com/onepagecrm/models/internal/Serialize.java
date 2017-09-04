package com.onepagecrm.models.internal;

import com.onepagecrm.net.ApiResource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 04/09/2017.
 */
public interface Serialize<T extends ApiResource> {

    T fromJsonObject(JSONObject apiResourceObject);

    List<T> fromJsonArray(JSONArray apiResourceArray);

    JSONObject toJsonObject(T apiResource);

    JSONArray toJsonArray(List<T> apiResourceList);

    String toJsonString(T apiResource);

    String toJsonString(List<T> apiResourceList);
}
