package com.onepagecrm.net.request;

import com.onepagecrm.net.AuthData;

@SuppressWarnings("WeakerAccess")
public abstract class SignedRequest extends Request {

    private AuthData authData;

    public void setAuthData(AuthData authData) {
        this.authData = authData;
    }

    public AuthData getAuthData() {
        return authData;
    }
}
