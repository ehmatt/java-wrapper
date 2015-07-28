package com.onepagecrm.net.request;

import java.util.HashMap;

public class LoginRequest extends Request {

	public LoginRequest(String username, String password) {
		params = new HashMap<String, String>();
		params.put("login", username);
		params.put("password", password);
		initialize();
	}

	private void initialize() {
		setEndpointUrl(null);
		setType();
		setRequestBody();
	}

	@Override
	public void setEndpointUrl(String url) {
		endpointUrl = baseUrl + "login" + format;
	}

	@Override
	public void setType() {
		this.type = Type.POST;
	}
}
