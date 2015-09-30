package com.onepagecrm.net;

import com.onepagecrm.models.BaseResource;

public abstract class ApiResource extends BaseResource {

    public static final String CHARSET = "UTF-8";

    public static final String ACTION_STREAM_ENDPOINT = "action_stream";
    public static final String CONTACTS_ENDPOINT = "contacts";
    public static final String CALLS_ENDPOINT = "calls";
    public static final String COUNTRIES_ENDPOINT = "countries"; // -> Countries class as doesn't inherit from here

    public String id;

    public abstract String getId();

    public abstract ApiResource setId(String id);

    @Override
    public abstract String toString();
}
