package com.onepagecrm.net.request;

import com.onepagecrm.models.serializers.BaseSerializer;
import com.onepagecrm.models.serializers.LoginSerializer;

public class LoginRequest extends Request {

    public LoginRequest(String username, String password, boolean fullResponse) {
        setType();
        setEndpointUrl(BaseSerializer.LOGIN_TAG);
        this.requestBody = LoginSerializer.toJsonObject(username, password, fullResponse);
    }

    @Override
    public void setType() {
        this.type = Type.POST;
    }
}
