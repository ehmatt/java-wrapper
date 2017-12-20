package com.onepagecrm.models.serializers.impl;

import com.onepagecrm.models.BaseResource;
import com.onepagecrm.models.serializers.BaseSerializer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 04/09/2017.
 */
public abstract class BaseSerializable<T extends BaseResource>
        extends BaseSerializer implements Serializable<T> {

    @Override
    public T fromJsonObject(JSONObject baseResourceObject) {
        return null;
    }

    @Override
    public List<T> fromJsonArray(JSONArray baseResourceArray) {
        return null;
    }

    @Override
    public JSONObject toJsonObject(T baseResource) {
        return null;
    }

    @Override
    public JSONArray toJsonArray(List<T> baseResourceList) {
        return null;
    }

    @Override
    public String toJsonString(T baseResource) {
        return null;
    }

    @Override
    public String toJsonString(List<T> baseResourceList) {
        return null;
    }
}
