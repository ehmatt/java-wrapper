package com.onepagecrm.net.request;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.net.OnePageAuthData;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 28/04/2017.
 */
public abstract class OnePageSignedRequest extends SignedRequest {

    @Override
    public void setRequestHeaders() {
        super.setRequestHeaders();

        if (getAuthData() == null || getAuthData().getUserId() == null) {
            return;
        }

        OnePageAuthData authData = (OnePageAuthData) getAuthData();
        connection.setRequestProperty(X_UID, authData.getUserId());
        LOG.info("X_UID=" + authData.getUserId());
        connection.setRequestProperty(X_TS, Integer.toString(authData.getUnixTime()));
        LOG.info("X_TS=" + Integer.toString(authData.getUnixTime()));
        connection.setRequestProperty(X_AUTH, authData.getSignature());
        LOG.info("X_AUTH=" + authData.getSignature());
        connection.setRequestProperty(X_SOURCE, OnePageCRM.SOURCE);
        LOG.info("X_SOURCE=" + OnePageCRM.SOURCE);

    }
}
