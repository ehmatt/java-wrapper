package com.onepagecrm.net.request;

import java.util.Map;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class PostRequest extends SignedRequest {

    public PostRequest(String endpoint, String query, Map<String, String> params) {
        this.params = params;
        setType();
        setEndpointUrl(endpoint);
        if (query == null) {
            authenticate();
        } else {
            addQuery(query);
            authenticate();
        }
    }

    @Override
    public void setType() {
        this.type = Type.POST;
    }

    public PostRequest addQuery(String query) {
        this.endpointUrl += query;
        return this;
    }

    public void authenticate() {
        setRequestBody();
        authData = new Authentication(Account.loggedInUser, Request.POST, endpointUrl, requestBody);
    }
}
