package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class PatchRequest extends SignedRequest {

    public PatchRequest(String endpoint) {
	setType();
	setEndpointUrl(endpoint);
	authData = new Authentication(Account.loggedInUser, Request.PATCH, endpointUrl, "");
    }

    @Override
    public void setType() {
	this.type = Type.PATCH;
    }
}
