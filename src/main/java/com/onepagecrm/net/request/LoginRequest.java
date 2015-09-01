package com.onepagecrm.net.request;

import java.util.HashMap;

public class LoginRequest extends Request {

    public LoginRequest(String username, String password) {
        params = new HashMap<String, String>();
        params.put("login", username);
        params.put("password", password);
        setType();
        setEndpointUrl("login");
    }

    @Override
    public void setType() {
        this.type = Type.POST;
    }
}
