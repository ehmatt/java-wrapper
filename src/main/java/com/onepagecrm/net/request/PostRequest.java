package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class PostRequest extends SignedRequest {

	public PostRequest(String endpoint) {
		setType();
		setEndpointUrl(endpoint);
		authData = new Authentication(Account.loggedInUser, Request.POST, endpointUrl, "");
	}
	
	@Override
	public void setType() {
		this.type = Type.POST;
	}
}
