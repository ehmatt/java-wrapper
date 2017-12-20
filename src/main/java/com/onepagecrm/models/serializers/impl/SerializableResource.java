package com.onepagecrm.models.serializers.impl;

import com.onepagecrm.models.BaseResource;
import com.onepagecrm.models.serializers.BaseSerializer;
import com.sun.istack.internal.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 04/09/2017.
 */
public abstract class SerializableResource<T extends BaseResource> extends BaseSerializer implements Serializable<T> {

    protected abstract T singleResource();

    protected abstract String singleTag();

    protected List<T> multipleResources() {
        return new ArrayList<>();
    }

    protected abstract String multipleTag();

    @Override
    public T fromJsonObject(JSONObject resourceObject) {
        if (resourceObject == null) return singleResource();
        return fromJsonObjectImpl(resourceObject);
    }

    protected abstract T fromJsonObjectImpl(@NotNull JSONObject resourceObject);

    @Override
    public List<T> fromJsonArray(JSONArray resourceArray) {
        if (resourceArray == null) return multipleResources();
        return fromJsonArrayImpl(resourceArray);
    }

    protected List<T> fromJsonArrayImpl(@NotNull JSONArray resourceArray) {
        List<T> list = multipleResources();
        for (int i = 0; i < resourceArray.length(); i++) {
            list.add(fromJsonObject(resourceArray.optJSONObject(i)));
        }
        return list;
    }

    @Override
    public JSONObject toJsonObject(T resource) {
        if (resource == null) return EMPTY_JSON_OBJECT;
        return toJsonObjectImpl(resource);
    }

    protected abstract JSONObject toJsonObjectImpl(@NotNull T resource);

    @Override
    public JSONArray toJsonArray(List<T> resourceList, boolean includeObjectKey) {
        if (resourceList == null || resourceList.isEmpty()) {
            return EMPTY_JSON_ARRAY;
        }
        return toJsonArrayImpl(resourceList, includeObjectKey);
    }

    protected JSONArray toJsonArrayImpl(@NotNull List<T> resourceList, boolean includeObjectKey) {
        JSONArray resourceArray = new JSONArray();
        for (T item : resourceList) {
            JSONObject outerObject = new JSONObject();
            JSONObject innerObject = toJsonObject(item);
            addJsonObject(innerObject, outerObject, singleTag());
            resourceArray.put(includeObjectKey ? outerObject : innerObject);
        }
        return resourceArray;
    }

    @Override
    public String toJsonString(T resource) {
        return toJsonObject(resource).toString();
    }

    @Override
    public String toJsonString(List<T> resourceList, boolean includeObjectKey) {
        return toJsonArray(resourceList, includeObjectKey).toString();
    }
}
