package com.onepagecrm.api.net;

//import android.util.Log;

//import com.onepagecrm.onepagecrmdialer.utilities.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class MakeRequest {

    private static final String TAG = MakeRequest.class.getSimpleName();

    private static final String USER_AGENT = "Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; " +
            "LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";

    private static final String X_UID = "x-onepagecrm-uid";
    private static final String X_TS = "x-onepagecrm-ts";
    private static final String X_AUTH = "x-onepagecrm-auth";

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
//            Log.e(TAG, "Error forming URL for GET request");
            e.printStackTrace();
        }
        response = null;

        try {
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", USER_AGENT);

//            Log.i(TAG, "*************************************");
//            Log.i(TAG, "--- REQUEST ---");
//            Log.i(TAG, "Type: " + authSignature.getType());
//            Log.i(TAG, "URL: " + requestUrl.toString());
//            Log.i(TAG, "Body: " + authSignature.getRequestBody());

            if (authSignature.getUserId() != null) {
                conn.setRequestProperty(X_UID, authSignature.getUserId());
//                Log.i(TAG, "X_UID=" + authSignature.getUserId());
                conn.setRequestProperty(X_TS, Integer.toString(authSignature.getUnixTime()));
//                Log.i(TAG, "X_TS=" + Integer.toString(Utils.getUnixTime()));
                conn.setRequestProperty(X_AUTH, authSignature.getSignature());
//                Log.i(TAG, "X_AUTH=" + authSignature.getSignature());
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

            System.out.println(responseBody);

//            Log.i(TAG, "--- RESPONSE ---");
//            Log.i(TAG, "Code: " + responseCode);
//            Log.i(TAG, "Message: " + responseMessage);
//            Log.i(TAG, "Body: " + responseBody);
//            Log.i(TAG, "*************************************");

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
//            Log.e(TAG, "GET request failed");
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

//            Log.i(TAG, "*************************************");
//            Log.i(TAG, "--- REQUEST ---");
//            Log.i(TAG, "Type: " + authSignature.getType());
//            Log.i(TAG, "URL: " + requestUrl.toString());
//            Log.i(TAG, "Body: " + authSignature.getRequestBody());

            if (authSignature.getUserId() != null) {
                conn.setRequestProperty(X_UID, authSignature.getUserId());
//                Log.i(TAG, "X_UID=" + authSignature.getUserId());
                conn.setRequestProperty(X_TS, Integer.toString(authSignature.getUnixTime()));
//                Log.i(TAG, "X_TS=" + Integer.toString(Utils.getUnixTime()));
                conn.setRequestProperty(X_AUTH, authSignature.getSignature());
//                Log.i(TAG, "X_AUTH=" + authSignature.getSignature());
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
            
            System.out.println(responseBody);

//            Log.i(TAG, "--- RESPONSE ---");
//            Log.i(TAG, "Code: " + responseCode);
//            Log.i(TAG, "Message: " + responseMessage);
//            Log.i(TAG, "Body: " + responseBody);
//            Log.i(TAG, "*************************************");

            conn.disconnect();

        } catch (IOException e) {
//            Log.e(TAG, "POST request failed");
            e.printStackTrace();
        }

        return response;
    }
}