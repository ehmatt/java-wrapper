package com.onepagecrm.models.serializers.impl;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.serializers.BaseSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 20/12/2017.
 */
public abstract class APISerializable<T extends ApiResource> extends BaseSerializable<T> {

    public T single(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        return fromJsonObject(dataObject);
    }

    public List<T> list(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        JSONArray resourceArray = dataObject.optJSONArray(multipleTag());
        return fromJsonArray(resourceArray);
    }
}
