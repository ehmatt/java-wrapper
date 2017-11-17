package com.onepagecrm.net.request;

import org.json.JSONException;
import org.json.JSONObject;

public class GoogleLoginRequest extends Request {

    public GoogleLoginRequest(String oauth2Code, boolean login) {
        setType();
        setEndpointUrl(login ? "google_plus" : "google_plus/signup");
        JSONObject params = new JSONObject();
        try {
            params.put("code", oauth2Code);
            this.requestBody = params.toString();
        } catch (JSONException e) {
            LOG.severe("Error creating JSON");
            LOG.severe(e.toString());
        }
    }

    @Override
    public void setType() {
        this.type = Type.POST;
    }
}
