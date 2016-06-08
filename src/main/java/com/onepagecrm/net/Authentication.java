package com.onepagecrm.net;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.models.User;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class Authentication {

    private static final Logger LOG = Logger.getLogger(Authentication.class.getName());

    private String userId;
    private String apiKey;
    private int timestamp;
    private String type;
    private String url;
    private String body;
    private String signature;

    /**
     * Constructor which will only be used for login (no user details known
     * yet).
     *
     * @param type
     * @param url
     * @param body
     */
    public Authentication(String type, String url, String body) {
        this.type = type;
        this.url = url;
        this.body = body;
    }

    /**
     * Constructor which will be used for every API other than logging in.
     *
     * @param user
     * @param type
     * @param url
     * @param body
     */
    public Authentication(User user, String type, String url, String body) {
        this.userId = user.getId();
        this.apiKey = user.getAuthKey();
        this.timestamp = getUnixTime();
        this.type = type;
        this.url = url;
        this.body = body;
        this.signature = calculateSignature();
    }

    /**
     * Constructor used for testing to manually enter/fabricate timestamp.
     *
     * @param user
     * @param type
     * @param url
     * @param body
     */
    public Authentication(User user, int timestamp, String type, String url, String body) {
        this.userId = user.getId();
        this.apiKey = user.getAuthKey();
        this.timestamp = timestamp;
        this.type = type;
        this.url = url;
        this.body = body;
        this.signature = calculateSignature();
    }

    /**
     * Method which handles the process of calculating auth data i.e.
     * X-OnePageCRM-Auth.
     *
     * @return
     */
    private String calculateSignature() {
        byte[] decodedApiKey = new byte[0];
        try {
            decodedApiKey = Base64.decodeBase64(apiKey.getBytes("UTF-8"));
        } catch (IOException e) {
            LOG.severe("Error decoding the ApiKey");
            LOG.severe(e.toString());
        }
        String urlHash = convertStringToSha1Hash(url);
        if (OnePageCRM.DEBUG) {
            LOG.info("URL=\'" + url + "\'");
            LOG.info("hash(URL)=\'" + urlHash + "\'");
        }
        String signature = userId + "." + timestamp + "." + type.toUpperCase() + "." + urlHash;
        if (type.equals("POST") || type.equals("PUT")) {
            if (body != null) {
                String bodyHash = convertStringToSha1Hash(body);
                signature += "." + bodyHash;
                if (OnePageCRM.DEBUG) {
                    LOG.info("BODY=\'" + body + "\'");
                    LOG.info("hash(BODY)=\'" + bodyHash + "\'");
                }
            }
        }
        if (OnePageCRM.DEBUG) {
            LOG.info("Signature=\'" + signature + "\'");
        }
        return makeHMACSHA256Signature(decodedApiKey, signature);
    }

    /**
     * Acquires the SHA-1 hash of a given Url.
     *
     * @param url
     * @return
     */
    @SuppressWarnings("ConstantConditions")
    private String convertStringToSha1Hash(String url) {
        MessageDigest encoder = null;
        try {
            encoder = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            LOG.severe("Could not use SHA1 hashing algorithm");
            LOG.severe(e.toString());
        }
        byte[] urlBuffer = url.getBytes(Charset.forName("UTF-8"));
        encoder.reset();
        encoder.update(urlBuffer);
        return new String(Hex.encodeHex(encoder.digest()));
    }

    /**
     * Delivers the final signature i.e. X-OnePageCRM-Auth.
     *
     * @param apiKey
     * @param signature
     * @return
     */
    private String makeHMACSHA256Signature(byte[] apiKey, String signature) {
        byte[] signatureBuffer = signature.getBytes(Charset.forName("UTF-8"));
        SecretKey secretKey = new SecretKeySpec(apiKey, "HMACSHA256");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HMACSHA256");
        } catch (NoSuchAlgorithmException e) {
            LOG.severe("Could not use SHA256 hashing algorithm");
            LOG.severe(e.toString());
        }
        try {
            mac.init(secretKey);
        } catch (InvalidKeyException e) {
            LOG.severe("Error decoding the ApiKey");
            LOG.severe(e.toString());
        }
        String sha256Hash = new String(Hex.encodeHex(mac.doFinal(signatureBuffer)));
        if (OnePageCRM.DEBUG) {
            LOG.info("hash(Signature)=\'" + sha256Hash + "\'");
        }
        return sha256Hash;
    }

    /**
     * Method acquires the current Unix-style time.
     * <p/>
     * Unix-style time is the amount of milliseconds elapsed since 01 Jan 1970.
     *
     * @return
     */
    public int getUnixTime() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public String getUserId() {
        return userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public String getSignature() {
        return signature;
    }
}
