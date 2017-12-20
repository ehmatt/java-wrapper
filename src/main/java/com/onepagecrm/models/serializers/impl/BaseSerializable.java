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

    protected abstract T singleResource();

    protected abstract String singleTag();

    protected abstract List<T> multipleResources();

    protected abstract String multipleTag();

    @Override
    public T fromJsonObject(JSONObject baseResourceObject) {
        if (baseResourceObject == null) return singleResource();
        return fromJsonObjectImpl(baseResourceObject);
    }

    protected abstract T fromJsonObjectImpl(JSONObject baseResourceObject);

    @Override
    public List<T> fromJsonArray(JSONArray baseResourceArray) {
        if (baseResourceArray == null) return multipleResources();
        return fromJsonArrayImpl(baseResourceArray);
    }

    protected List<T> fromJsonArrayImpl(JSONArray baseResourceArray) {
        List<T> list = multipleResources();
        for (int i = 0; i < baseResourceArray.length(); i++) {
            list.add(fromJsonObject(baseResourceArray.optJSONObject(i)));
        }
        return list;
    }

    @Override
    public JSONObject toJsonObject(T baseResource) {
        if (baseResource == null) return EMPTY_JSON_OBJECT;
        return toJsonObjectImpl(baseResource);
    }

    protected abstract JSONObject toJsonObjectImpl(T baseResource);

    @Override
    public JSONArray toJsonArray(List<T> baseResourceList) {
        if (baseResourceList == null || baseResourceList.isEmpty()) {
            return EMPTY_JSON_ARRAY;
        }
        return toJsonArrayImpl(baseResourceList);
    }

    protected JSONArray toJsonArrayImpl(List<T> baseResourceList) {
        JSONArray baseResourceArray = new JSONArray();
        for (T item : baseResourceList) {
            baseResourceArray.put(toJsonObject(item));
        }
        return baseResourceArray;
    }

    @Override
    public String toJsonString(T baseResource) {
        return toJsonObject(baseResource).toString();
    }

    @Override
    public String toJsonString(List<T> baseResourceList) {
        return toJsonArray(baseResourceList).toString();
    }
}
