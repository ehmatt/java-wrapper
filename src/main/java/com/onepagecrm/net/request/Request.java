package com.onepagecrm.net.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.onepagecrm.models.serializer.BaseSerializer;
import com.onepagecrm.net.Response;

public abstract class Request {

    protected static final Logger LOG = Logger.getLogger(Request.class.getName());

    public static boolean isProdApp = false;

    protected static final String baseUrl = "https://app.onepagecrm.com/api/v3/";
    protected static final String baseDevUrl = "http://staging.onepagecrm.com/api/v3/";
    protected static final String format = ".json";
    protected String endpointUrl;

    protected static enum Type {
        GET, POST, PUT, DELETE, PATCH
    }

    protected Type type = Type.GET; // default type GET

    protected static final String GET = "GET";
    protected static final String POST = "POST";
    protected static final String PUT = "PUT";
    protected static final String DELETE = "DELETE";
    protected static final String PATCH = "PATCH";

    protected String requestBody = "";
    protected Map<String, String> params;
    protected Response response;

    private static final String ACCECPTS_TAG = "Accepts";
    private static final String ACCECPTS = "application/json";

    private static final String CONTENT_TYPE_TAG = "Content-Type";
    private static final String CONTENT_TYPE = "application/json";

    private static final String USER_AGENT_TAG = "User-Agent";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) "
            + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.89 Safari/537.36";

    protected static final String X_UID = "X-OnePageCRM-UID";
    protected static final String X_TS = "X-OnePageCRM-TS";
    protected static final String X_AUTH = "X-OnePageCRM-AUTH";
    protected static final String X_SOURCE = "X-OnePageCRM-SOURCE";

    public static String SOURCE = "java-client";

    protected HttpURLConnection connection;

    public abstract void setType();

    public void setEndpointUrl(String enpoint) {
        if (isProdApp) {
            endpointUrl = baseUrl + enpoint + format;
        } else {
            endpointUrl = baseDevUrl + enpoint + format;
        }
    }

    /**
     * Method require to send HTTP request.
     *
     * @return
     */
    public Response send() {
        setupAndConnect();
        setRequestMethod();
        setRequestBody();
        setRequestHeaders();
        writeRequestBody();
        getResponse();
        connection.disconnect();
        return response;
    }

    /**
     * Connect to URL using HttpURLConnection class.
     */
    private void setupAndConnect() {
        URL url = getUrl(this.endpointUrl);
        connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
        } catch (IOException e) {
            LOG.severe("Error connecting to URL : " + url);
            LOG.severe(e.toString());
        }
    }

    /**
     * Convert String to URL object.
     *
     * @param url
     * @return
     */
    private URL getUrl(String url) {
        URL requestUrl = null;
        try {
            requestUrl = new URL(url);
        } catch (MalformedURLException e) {
            LOG.severe("Error forming URL for GET request");
            LOG.severe(e.toString());
        }
        return requestUrl;
    }

    /**
     * Set HTTP request method e.g. GET, POST etc.
     */
    private void setRequestMethod() {
        switch (type) {
            case GET:
                try {
                    connection.setRequestMethod(GET);
                } catch (ProtocolException e) {
                    LOG.severe("Could not set request as GET successfully");
                    LOG.severe(e.toString());
                }
                break;
            case POST:
                connection.setDoOutput(true);
                try {
                    connection.setRequestMethod(POST);
                } catch (ProtocolException e) {
                    LOG.severe("Could not set request as POST successfully");
                    LOG.severe(e.toString());
                }
                break;
            case PUT:
                try {
                    connection.setRequestMethod(PUT);
                } catch (ProtocolException e) {
                    LOG.severe("Could not set request as PUT successfully");
                    LOG.severe(e.toString());
                }
                break;
            case DELETE:
                try {
                    connection.setRequestMethod(DELETE);
                } catch (ProtocolException e) {
                    LOG.severe("Could not set request as DELETE successfully");
                    LOG.severe(e.toString());
                }
                break;
            case PATCH:
                try {
                    connection.setRequestMethod(PATCH);
                } catch (ProtocolException e) {
                    LOG.severe("Could not set request as PATCH successfully");
                    LOG.severe(e.toString());
                }
                break;
        }
    }

    /**
     * Define the headers for the request. This method will be overriden in
     * SignedRequest to include auth headers.
     */
    public void setRequestHeaders() {
        connection.setRequestProperty(ACCECPTS_TAG, ACCECPTS);
        connection.setRequestProperty(USER_AGENT_TAG, USER_AGENT);
        connection.setRequestProperty(CONTENT_TYPE_TAG, CONTENT_TYPE);

        LOG.info("*************************************");
        LOG.info("--- REQUEST ---");
        LOG.info("Type: " + connection.getRequestMethod());
        LOG.info("URL: " + connection.getURL());
        LOG.info("Body: " + requestBody);
    }

    protected void setRequestBody() {
        if (this.requestBody.equals("")) {
            this.requestBody = BaseSerializer.encodeParams(params);
        }
    }

//    /**
//     * Encode request parameters.
//     *
//     * @param params
//     * @return
//     */
//    private String encodeParams(Map<String, String> params) {
//        if (params != null && !params.isEmpty()) {
//            String encodedString = "";
//            int i = 0;
//            for (Map.Entry<String, String> param : params.entrySet()) {
//                if (i > 0) {
//                    encodedString += "&";
//                }
//                try {
//                    encodedString += String.format("%s=%s",
//                            URLEncoder.encode(param.getKey(), "UTF-8"),
//                            URLEncoder.encode(param.getValue(), "UTF-8"));
//                } catch (UnsupportedEncodingException e) {
//                    LOG.severe("Error encoding url params : " + params.toString());
//                    LOG.severe(e.toString());
//                } finally {
//                    i++;
//                }
//            }
//            return encodedString;
//        }
//        return "";
//    }

    /**
     * Actually write the request body using the OutputStreamWriter.
     */
    private void writeRequestBody() {
        if (requestBody != null && !requestBody.equals("")) {
            OutputStreamWriter out = null;
            try {
                out = new OutputStreamWriter(connection.getOutputStream());
            } catch (IOException e) {
                LOG.severe("Could not open output stream to write request body");
                LOG.severe(e.toString());
            }
            try {
                out.write(requestBody);
            } catch (IOException e) {
                LOG.severe("Could not write request body");
                LOG.severe(e.toString());
            }
            try {
                out.flush();
            } catch (IOException e) {
                LOG.severe("Could not flush output stream for writing request body");
                LOG.severe(e.toString());
            }
        }
    }

    /**
     * Acquire the HTTP response code, message and body.
     */
    private void getResponse() {
        response = new Response();

        getResponseCode();
        getResponseMessage();
        getResponseBody();

        LOG.info("--- RESPONSE ---");
        LOG.info("Code: " + response.getResponseCode());
        LOG.info("Message: " + response.getResponseMessage());
        LOG.info("Body: " + response.getResponseBody());
        LOG.info("*************************************");
    }

    private void getResponseCode() {
        try {
            response.setResponseCode(connection.getResponseCode());
        } catch (IOException e) {
            LOG.severe("Could not get response code");
            LOG.severe(e.toString());
        }
    }

    private void getResponseMessage() {
        try {
            response.setResponseMessage(connection.getResponseMessage());
        } catch (IOException e) {
            LOG.severe("Could not get response message");
            LOG.severe(e.toString());
        }
    }

    private void getResponseBody() {
        switch (type) {
            case GET:
                response.setResponseBody(getGetResponseBody());
                break;
            case POST:
                response.setResponseBody(getPostResponseBody());
                break;
            case PUT:
            case PATCH:
            case DELETE:
                response.setResponseBody(getGetResponseBody());
                break;
        }
    }

    private String getGetResponseBody() {
        Scanner scan = null;
        String responseBody = "";
        if (response.getResponseCode() < 300) {
            try {
                scan = new Scanner(connection.getInputStream());
            } catch (IOException e) {
                LOG.severe("Could not get response body");
                LOG.severe(e.toString());
            }
        } else {
            scan = new Scanner(connection.getErrorStream());
        }

        while (scan.hasNext()) {
            responseBody += scan.nextLine();
        }
        scan.close();
        return responseBody;
    }

    private String getPostResponseBody() {
        BufferedReader br = null;
        String responseBody = "";
        if (response.getResponseCode() < 300) {
            try {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                LOG.severe("Could not open input stream to get " + "response body of POST request");
                LOG.severe(e.toString());
            }
        } else {
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        String output;
        try {
            while ((output = br.readLine()) != null) {
                responseBody += output;
            }
        } catch (IOException e) {
            LOG.severe("Could not read line using buffered reader");
            LOG.severe(e.toString());
        }
        try {
            br.close();
        } catch (IOException e) {
            LOG.severe("Could not close buffered reader");
            LOG.severe(e.toString());
        }
        return responseBody;
    }
}
