package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializers.TagSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.DeleteRequest;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tag extends BaseResource implements Serializable {

    private static final String TAGS_ENDPOINT = ApiResource.TAGS_ENDPOINT;

    private String name;
    private Integer count;
    private Integer totalCount;
    private Integer actionStreamCount;

    public static List<Tag> list() throws OnePageException {
        Map<String, Object> params = new HashMap<>();
        params.put("per_page", 100);
        Request request = new GetRequest(
                TAGS_ENDPOINT,
                Query.fromParams(params)
        );
        Response response = request.send();
        return TagSerializer.fromString(response.getResponseBody());
    }

    public boolean save() throws OnePageException {
        Request request = new PostRequest(
                TAGS_ENDPOINT,
                null,
                TagSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return TagSerializer.fromStringSave(response.getResponseBody());
    }

    public boolean delete() throws OnePageException {
        Request request = new DeleteRequest(addNameToEndpoint(TAGS_ENDPOINT));
        Response response = request.send();
        return TagSerializer.fromStringDelete(response.getResponseBody());
    }

    private String addNameToEndpoint(String endpoint) {
        return endpoint + "/" + this.name;
    }

    public Tag() {
    }

    public Tag(Tag tag) {
        this.name = tag.getName();
        this.count = tag.getCount();
        this.totalCount = tag.getTotalCount();
        this.actionStreamCount = tag.getActionStreamCount();
    }

    @Override
    public String toString() {
        return TagSerializer.toJsonObject(this);
    }

    /**
     * Method to compare Tags to one another based off of their name.
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Tag) {
            Tag toCompare = (Tag) object;
            if (this.name != null && toCompare.name != null) {
                return this.name.equals(toCompare.name);
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Tag setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Tag setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public Integer getActionStreamCount() {
        return actionStreamCount;
    }

    public Tag setActionStreamCount(Integer actionStreamCount) {
        this.actionStreamCount = actionStreamCount;
        return this;
    }
}
