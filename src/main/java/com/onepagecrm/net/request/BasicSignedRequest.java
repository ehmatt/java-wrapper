package com.onepagecrm.net.request;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.net.BasicAuthData;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 28/04/2017.
 */
public abstract class BasicSignedRequest extends SignedRequest {

    @Override
    public void setRequestHeaders() {
        super.setRequestHeaders();

        if (getAuthData() == null || getAuthData().getUserId() == null) {
            return;
        }

        BasicAuthData authData = (BasicAuthData) getAuthData();
        connection.setRequestProperty(AUTHORIZATION, authData.getSignature());
        LOG.info("Authorization=" + authData.getSignature());
        connection.setRequestProperty(X_SOURCE, OnePageCRM.SOURCE);
        LOG.info("X_SOURCE=" + OnePageCRM.SOURCE);
    }
}
