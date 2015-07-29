package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class GetRequest extends SignedRequest {
	
	public GetRequest(String endpoint) {
		setType();
		setEndpointUrl(endpoint);
		authData = new Authentication(Account.loggedInUser, Request.GET, endpointUrl, "");
	}

	@Override
	public void setType() {
		this.type = Type.GET;
	}
}