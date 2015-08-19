package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class PutRequest extends SignedRequest {

    public PutRequest(String endpoint) {
	setType();
	setEndpointUrl(endpoint);
	authData = new Authentication(Account.loggedInUser, Request.PUT, endpointUrl, "");
    }

    @Override
    public void setType() {
	this.type = Type.PUT;
    }
}
