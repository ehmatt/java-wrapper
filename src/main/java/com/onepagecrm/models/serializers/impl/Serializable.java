package com.onepagecrm.models.serializers.impl;

import com.onepagecrm.models.BaseResource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 04/09/2017.
 */
public interface Serializable<T extends BaseResource> {

    T defaultSingle();

    List<T> defaultList();

    T fromJsonObject(JSONObject baseResourceObject);

    T fromJsonObjectImpl(JSONObject baseResourceObject);

    List<T> fromJsonArray(JSONArray baseResourceArray);

    List<T> fromJsonArrayImpl(JSONArray baseResourceArray);

    JSONObject toJsonObject(T baseResource);

    JSONObject toJsonObjectImpl(T baseResource);

    JSONArray toJsonArray(List<T> baseResourceList);

    JSONArray toJsonArrayImpl(List<T> baseResourceList);

    String toJsonString(T baseResource);

    String toJsonString(List<T> baseResourceList);
}
