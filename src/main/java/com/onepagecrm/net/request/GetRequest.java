package com.onepagecrm.net.request;

import java.util.Map;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class GetRequest extends SignedRequest {

    public GetRequest(String endpoint) {
	setType();
	setEndpointUrl(endpoint);
	authData = new Authentication(Account.loggedInUser, Request.GET, endpointUrl, requestBody);
    }

    public GetRequest(String endpoint, String query, Map<String, String> params) {
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
	this.type = Type.GET;
    }

    public GetRequest addQuery(String query) {
	this.endpointUrl += query;
	return this;
    }

    public void authenticate() {
	setRequestBody();
	authData = new Authentication(Account.loggedInUser, Request.GET, endpointUrl, requestBody);
    }
}