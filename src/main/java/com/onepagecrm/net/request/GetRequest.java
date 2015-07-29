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
	public void setAuthData(Authentication authData) {
		this.authData = authData;
	}
	
	@Override
	public void setEndpointUrl(String endpoint) {
		endpointUrl = baseUrl + endpoint + format;
	}

	@Override
	public void setType() {
		this.type = Type.GET;
	}
}