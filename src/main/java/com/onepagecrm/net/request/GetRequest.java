package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.BasicAuthData;

@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class GetRequest extends BasicSignedRequest {

    public GetRequest(String endpoint) {
        setType();
        setEndpointUrl(endpoint);
        //setAuthData(new OnePageAuthData(Account.loggedInUser, Request.GET, endpointUrl, requestBody));
        setAuthData(new BasicAuthData(Account.loggedInUser));
    }

    public GetRequest(String endpoint, String query) {
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
        this.type = Type.GET;
    }

    public GetRequest addQuery(String query) {
        this.endpointUrl += query;
        return this;
    }

    public void authenticate() {
        setRequestBody();
        //setAuthData(new OnePageAuthData(Account.loggedInUser, Request.GET, endpointUrl, requestBody));
        setAuthData(new BasicAuthData(Account.loggedInUser));
    }
}