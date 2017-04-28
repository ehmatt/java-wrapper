package com.onepagecrm.net;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.models.User;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

public class BasicAuthData extends AuthData {

    private static final Logger LOG = Logger.getLogger(BasicAuthData.class.getName());

    /**
     * @param userId - Unique identifier of the user.
     * @param apiKey - Like the API password.
     */
    public BasicAuthData(String userId, String apiKey) {
        super(userId, apiKey);
        updateSignature();
    }

    /**
     * @param user - User to use when calculate authorization data.
     */
    public BasicAuthData(User user) {
        super(user.getId(), user.getAuthKey());
        updateSignature();
    }

    /**
     * Method which handles the process of calculating auth data i.e.
     * "Authorization" - "Basic #{Base64.encode("userId:apiKey")}".
     *
     * @return
     */
    public String calculateSignature() {
        if (OnePageCRM.DEBUG) {
            LOG.info("*************************************");
            LOG.info("--- AUTHENTICATION ---");
        }
        String login = getUserId();
        String password = getApiKey();
        String toBeEncoded = String.format(Locale.ENGLISH, "%s:%s", login, password);
        if (OnePageCRM.DEBUG) {
            LOG.info("login=" + login);
            LOG.info("password=" + password);
            LOG.info("toBeEncoded=" + toBeEncoded);
        }
        String encodedAuthorization = "";
        try {
            encodedAuthorization = Base64.encodeBase64String(toBeEncoded.getBytes("UTF-8"));
        } catch (IOException e) {
            LOG.severe("Error encoding the auth data.");
            LOG.severe(e.toString());
        }
        String basicAuth = String.format(Locale.ENGLISH, "Basic %s", encodedAuthorization);
        if (OnePageCRM.DEBUG) {
            LOG.info("b64(toBeEncoded)=" + encodedAuthorization);
            LOG.info("Authorization=" + basicAuth);
        }
        return basicAuth;
    }
}
