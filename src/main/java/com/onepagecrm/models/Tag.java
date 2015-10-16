package com.onepagecrm.models;

import com.onepagecrm.models.serializer.TagSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.DeleteRequest;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;

public class Tag implements Serializable {

    private static final String TAGS_ENDPOINT = ApiResource.TAGS_ENDPOINT;

    private String name;
    private Integer counts;
    private Integer totalCounts;
    private Integer actionStreamCount;

    public String delete() {
        Request request = new DeleteRequest(TAGS_ENDPOINT + "/" + this.name);
        Response response = request.send();
        return response.getResponseBody();
    }

    public String save() {
        Request request = new PostRequest(
                TAGS_ENDPOINT,
                null,
                TagSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return response.getResponseBody();
    }

    public Tag() {
    }

    public Tag(Tag tag) {
        this.name = tag.getName();
        this.counts = tag.getCounts();
        this.totalCounts = tag.getTotalCounts();
        this.actionStreamCount = tag.getActionStreamCount();
    }

    @Override
    public String toString() {
        return TagSerializer.toJsonObject(this);
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getCounts() {
        return counts;
    }

    public Tag setCounts(Integer counts) {
        this.counts = counts;
        return this;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public Tag setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
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
