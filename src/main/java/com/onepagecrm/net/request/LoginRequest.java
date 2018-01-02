package com.onepagecrm.net.request;

import com.onepagecrm.models.internal.LoginData;
import com.onepagecrm.models.serializers.BaseSerializer;
import com.onepagecrm.models.serializers.LoginDataSerializer;

public class LoginRequest extends Request {

    public LoginRequest(String username, String password, boolean fullResponse) {
        this(new LoginData()
                .setUsername(username)
                .setPassword(password)
                .setFullResponse(fullResponse));
    }

    public LoginRequest(LoginData loginData) {
        setType();
        setEndpointUrl(BaseSerializer.LOGIN_TAG);
        this.requestBody = LoginDataSerializer.toJsonString(loginData);
    }

    @Override
    public void setType() {
        this.type = Type.POST;
    }
}
