package com.onepagecrm.net;

import com.onepagecrm.models.BaseResource;

public abstract class ApiResource extends BaseResource {

    public static final String CHARSET = "UTF-8";

    public static final String ACTION_STREAM_ENDPOINT = "action_stream";
    public static final String CONTACTS_ENDPOINT = "contacts";
    public static final String CALLS_ENDPOINT = "calls";

    protected String id;

    public String getId() {
        return this.id;
    }

    @Override
    public abstract String toString();
}
