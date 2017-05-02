package com.onepagecrm.net.request;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.net.AuthData;
import com.onepagecrm.net.BasicAuthData;
import com.onepagecrm.net.OnePageAuthData;

@SuppressWarnings("WeakerAccess")
public abstract class SignedRequest extends Request {

    private AuthData authData;

    @Override
    public void setRequestHeaders() {
        super.setRequestHeaders();

        if (getAuthData() == null || getAuthData().getUserId() == null) {
            return;
        }

        if (!OnePageCRM.COMPLEX_AUTH && getAuthData() instanceof BasicAuthData) {
            BasicAuthData authData = (BasicAuthData) getAuthData();
            connection.setRequestProperty(AUTHORIZATION, authData.getSignature());
            LOG.info("Authorization=" + authData.getSignature());
            connection.setRequestProperty(X_SOURCE, OnePageCRM.SOURCE);
            LOG.info("X_SOURCE=" + OnePageCRM.SOURCE);

        } else if (OnePageCRM.COMPLEX_AUTH && getAuthData() instanceof OnePageAuthData) {
            OnePageAuthData authData = (OnePageAuthData) getAuthData();
            connection.setRequestProperty(X_UID, authData.getUserId());
            LOG.info("X_UID=" + authData.getUserId());
            connection.setRequestProperty(X_TS, Integer.toString(authData.getTimestamp()));
            LOG.info("X_TS=" + Integer.toString(authData.getTimestamp()));
            connection.setRequestProperty(X_AUTH, authData.getSignature());
            LOG.info("X_AUTH=" + authData.getSignature());
            connection.setRequestProperty(X_SOURCE, OnePageCRM.SOURCE);
            LOG.info("X_SOURCE=" + OnePageCRM.SOURCE);
        }
    }

    public void setAuthData(AuthData authData) {
        this.authData = authData;
    }

    public AuthData getAuthData() {
        return authData;
    }
}
