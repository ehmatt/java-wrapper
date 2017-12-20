package com.onepagecrm.models.serializers.impl;

import com.onepagecrm.models.BaseResource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 04/09/2017.
 */
public interface Serializable<T extends BaseResource> {

    T fromJsonObject(JSONObject resourceObject);

    List<T> fromJsonArray(JSONArray resourceArray);

    JSONObject toJsonObject(T resource);

    JSONArray toJsonArray(List<T> resourceList);

    JSONArray toJsonArray(List<T> resourceList, boolean includeObjectKey);

    String toJsonString(T resource);

    String toJsonString(List<T> resourceList);

    String toJsonString(List<T> resourceList, boolean includeObjectKey);
}
