package com.onepagecrm.net.request;

import com.onepagecrm.net.Authentication;

public class PostRequest extends SignedRequest {

	@Override
	public void setAuthData(Authentication authData) {
		this.authData = authData;
	}
	
	@Override
	public void setEndpointUrl(String enpoint) {
		endpointUrl = baseUrl + enpoint;
	}
	
	@Override
	public void setType() {
		this.type = type.POST;
	}
}
