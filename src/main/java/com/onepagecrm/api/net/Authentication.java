package com.onepagecrm.api.net;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.onepagecrm.api.models.User;


public class Authentication {

    private static final String TAG = Authentication.class.getSimpleName();

    private String userId;
    private int timestamp;
    private String type;
    private String url;
    private String requestBody;
    private String signature;

    /**
     * Constructor which will only be used for login (no user details known yet).
     *
     * @param type
     * @param url
     * @param requestBody
     */
    public Authentication(String type, String url, String requestBody) {
        this.userId = null;
        this.type = type;
        this.url = url;
        this.requestBody = requestBody;
        this.signature = null;
    }

    /**
     * Constructor which will be used for every API other than logging in.
     *
     * @param user
     * @param type
     * @param url
     * @param requestBody
     */
    public Authentication(User user, String type, String url, String requestBody) {
        this.userId = user.getId();
        this.timestamp = getUnixTime();
        this.type = type;
        this.url = url;
        this.requestBody = requestBody;
        this.signature = calculateSignature(user.getId(), user.getAuthKey(), timestamp, type, url, requestBody);
    }

    /**
     * Constructor used for testing to fabricate timestamp.
     *
     * @param user
     * @param type
     * @param url
     * @param requestBody
     */
    public Authentication(User user, int timestamp, String type, String url, String requestBody) {
        this.userId = user.getId();
        this.timestamp = timestamp;
        this.type = type;
        this.url = url;
        this.requestBody = requestBody;
        this.signature = calculateSignature(user.getId(), user.getAuthKey(), timestamp, type, url, requestBody);
    }

    public static String calculateSignature(String UID, String APIKey, int timestamp, String requestType, String requestURL, String requestBody) {

        byte[] decodedAPIKey = new byte[0];
        try {
            decodedAPIKey = Base64.getDecoder().decode(APIKey.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
//            Log.e(TAG, "Could not decode API Key");
            e.printStackTrace();
        }
//        Log.d(TAG, "Encoded Api key: " + APIKey + " - Decoded Api key: " + decodedAPIKey + "\n");

        String requestURLHash = convertStringToSha1Hash(requestURL);
//        Log.d(TAG, "Request URL: " + requestURL + " - Hashed Request URL: " + requestURLHash + "\n");

        if (requestBody != null) {
            String requestBodyHash = convertStringToSha1Hash(requestBody);
//            Log.d(TAG, "Request Body: " + requestBody + " - Hashed Request Body: " + requestBodyHash + "\n");
        }

        String signatureMessage = UID + "." + timestamp + "." + requestType.toUpperCase() + "." + requestURLHash;
        if (requestType.equals("POST") || requestType.equals("PUT")) {
            signatureMessage += "." + convertStringToSha1Hash(requestBody);
        }
//        Log.d(TAG, "Signature Message: " + signatureMessage);

        return makeHMACSHA256Signature(decodedAPIKey, signatureMessage);
    }

    private static String convertStringToSha1Hash(String reqString) {

        MessageDigest encoder = null;
        try {
            encoder = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] reqBuffer = reqString.getBytes(Charset.forName("UTF-8"));

        encoder.reset();
        encoder.update(reqBuffer);

        String sha1Hash = new BigInteger(1, encoder.digest()).toString(16);
        return sha1Hash;
    }

    private static String makeHMACSHA256Signature(byte[] APIKey, String message) {

        byte[] signatureBuffer = message.getBytes(Charset.forName("UTF-8"));
        SecretKey key = new SecretKeySpec(APIKey, "HMACSHA256");

        Mac mac = null;
        try {
            mac = Mac.getInstance("HMACSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            mac.init(key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        String sha256Hash = new BigInteger(1, mac.doFinal(signatureBuffer)).toString(16);
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

    public String getSignature() {
        return signature;
    }

    public String getUserId() {
        return userId;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
