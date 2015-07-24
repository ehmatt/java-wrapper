package com.onepagecrm.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

import com.onepagecrm.models.ContactList;


public class MakeRequest {

//    private static final String TAG = MakeRequest.class.getSimpleName();
    protected static final Logger LOG = Logger.getLogger(ContactList.class.getName());

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) "
    		+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.89 Safari/537.36";

    private static final String X_UID = "X-OnePageCRM-UID";
    private static final String X_TS = "X-OnePageCRM-TS";
    private static final String X_AUTH = "X-OnePageCRM-AUTH";
    private static final String X_SOURCE = "X-OnePageCRM-SOURCE";
    
    private static final String SOURCE = "java-client";

    private Response response;
    private int responseCode = 0;
    private String responseMessage;
    private String responseBody;

    public MakeRequest() {}

    public Response getRequest(Authentication authSignature) {

        URL requestUrl = null;
        try {
            requestUrl = new URL(authSignature.getUrl());
        } catch (MalformedURLException e) {
            LOG.severe("Error forming URL for GET request");
            LOG.severe(e.toString());
        }
        response = null;

        try {
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", USER_AGENT);

            LOG.info("*************************************");
            LOG.info("--- REQUEST ---");
            LOG.info("Type: " + authSignature.getType());
            LOG.info("URL: " + requestUrl.toString());
            LOG.info("Body: " + authSignature.getRequestBody());

            if (authSignature.getUserId() != null) {
                conn.setRequestProperty(X_UID, authSignature.getUserId());
                LOG.info("X_UID=" + authSignature.getUserId());
                conn.setRequestProperty(X_TS, Integer.toString(authSignature.getUnixTime()));
                LOG.info("X_TS=" + Integer.toString(authSignature.getUnixTime()));
                conn.setRequestProperty(X_AUTH, authSignature.getSignature());
                LOG.info("X_AUTH=" + authSignature.getSignature());
                conn.setRequestProperty(X_SOURCE, SOURCE);
                LOG.info("X_SOURCE=" + SOURCE);
            }

            responseCode = conn.getResponseCode();
            responseMessage = conn.getResponseMessage();
            responseBody = "";

            Scanner scan;
            if (responseCode < 300)
                scan = new Scanner(conn.getInputStream());
            else
                scan = new Scanner(conn.getErrorStream());

            while (scan.hasNext()) {
                responseBody += scan.nextLine();
            }
            scan.close();

            response = new Response(responseCode, responseMessage, responseBody);

            LOG.info("--- RESPONSE ---");
            LOG.info("Code: " + responseCode);
            LOG.info("Message: " + responseMessage);
            LOG.info("Body: " + responseBody);
            LOG.info("*************************************");

            conn.disconnect();

        } catch (IOException e) {
            LOG.severe("GET request failed");
            LOG.severe(e.toString());
        }

        return response;
    }

    public Response postRequest(Authentication authSignature, String params) {

        URL requestUrl = null;
        try {
            requestUrl = new URL(authSignature.getUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        response = null;

        try {
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("User-Agent", USER_AGENT);

            LOG.info("*************************************");
            LOG.info("--- REQUEST ---");
            LOG.info("Type: " + authSignature.getType());
            LOG.info("URL: " + requestUrl.toString());
            LOG.info("Body: " + authSignature.getRequestBody());

            if (authSignature.getUserId() != null) {
                conn.setRequestProperty(X_UID, authSignature.getUserId());
                LOG.info("X_UID=" + authSignature.getUserId());
                conn.setRequestProperty(X_TS, Integer.toString(authSignature.getUnixTime()));
                LOG.info("X_TS=" + Integer.toString(authSignature.getUnixTime()));
                conn.setRequestProperty(X_AUTH, authSignature.getSignature());
                LOG.info("X_AUTH=" + authSignature.getSignature());
                conn.setRequestProperty(X_SOURCE, SOURCE);
                LOG.info("X_SOURCE=" + SOURCE);
            }

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(params);
            writer.flush();

            responseCode = conn.getResponseCode();
            responseMessage = conn.getResponseMessage();
            responseBody = "";

            BufferedReader br;

            if (responseCode < 300) {
                br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            } else {
                br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
            }

            String output;
            while ((output = br.readLine()) != null) {
                responseBody += output;
            }
            br.close();

            response = new Response(responseCode, responseMessage, responseBody);
            
            LOG.info("--- RESPONSE ---");
            LOG.info("Code: " + responseCode);
            LOG.info("Message: " + responseMessage);
            LOG.info("Body: " + responseBody);
            LOG.info("*************************************");

            conn.disconnect();

        } catch (IOException e) {
            LOG.severe("GET request failed");
            LOG.severe(e.toString());
        }

        return response;
    }
}