package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.BasicAuthData;

@SuppressWarnings({"WeakerAccess", "UnusedReturnValue", "unused"})
public class PatchRequest extends BasicSignedRequest {

    /**
     * Constructor which takes JSON string for request body.
     *
     * @param endpoint
     * @param query
     * @param jsonBody
     */
    public PatchRequest(String endpoint, String query, String jsonBody) {
        this.requestBody = jsonBody;
        setType();
        setEndpointUrl(endpoint);
        if (query == null) {
            authenticate();
        } else {
            addQuery(query);
            authenticate();
        }
    }

    public PatchRequest(String endpoint) {
        setType();
        setEndpointUrl(endpoint);
        //setAuthData(new OnePageAuthData(Account.loggedInUser, Request.PATCH, endpointUrl, ""));
        setAuthData(new BasicAuthData(Account.loggedInUser));
    }

    @Override
    public void setType() {
        this.type = Type.PATCH;
    }

    public PatchRequest addQuery(String query) {
        this.endpointUrl += query;
        return this;
    }

    public void authenticate() {
        setRequestBody();
        //setAuthData(new OnePageAuthData(Account.loggedInUser, Request.PATCH, endpointUrl, requestBody));
        setAuthData(new BasicAuthData(Account.loggedInUser));
    }
}
