package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class DeleteRequest extends SignedRequest {

    public DeleteRequest(String endpoint) {
	setType();
	setEndpointUrl(endpoint);
	authData = new Authentication(Account.loggedInUser, Request.DELETE, endpointUrl, "");
    }

    @Override
    public void setType() {
	this.type = Type.DELETE;
    }
}
