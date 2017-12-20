package com.onepagecrm.models.serializers.impl;

import com.onepagecrm.models.BaseResource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 04/09/2017.
 */
public interface Serializable<T extends BaseResource> {

    T fromJsonObject(JSONObject baseResourceObject);

    List<T> fromJsonArray(JSONArray baseResourceArray);

    JSONObject toJsonObject(T baseResource);

    JSONArray toJsonArray(List<T> baseResourceList);

    JSONArray toJsonArray(List<T> baseResourceList, boolean includeObjectKey);

    String toJsonString(T baseResource);

    String toJsonString(List<T> baseResourceList);

    String toJsonString(List<T> baseResourceList, boolean includeObjectKey);
}
