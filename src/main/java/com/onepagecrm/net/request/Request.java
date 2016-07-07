package com.onepagecrm.net.request;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.models.serializers.BaseSerializer;
import com.onepagecrm.net.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public abstract class Request {

    protected static final Logger LOG = Logger.getLogger(Request.class.getName());

    public static final int APP_SERVER = 0;
    public static final int DEV_SERVER = 1;
    public static final int STAGING_SERVER = 2;
    public static final int ATLAS_SERVER = 3;
    public static final int CALYPSO_SERVER = 4;
    public static final int DEIMOS_SERVER = 5;
    public static final int DRACO_SERVER = 6;
    public static final int GEMINI_SERVER = 7;
    public static final int ORION_SERVER = 8;
    public static final int PHOBOS_SERVER = 9;
    public static final int SECURE_SERVER = 10;
    public static final int TITAN_SERVER = 11;
    public static final int VOYAGER_SERVER = 12;
    public static final int LOCAL_DEV_SERVER = 13;
    public static final int NETWORK_DEV_SERVER = 14;
    public static final int MOCK_REQUEST_SERVER = 15;

    protected static final String APP_URL = "https://app.onepagecrm.com/api/v3/";
    protected static final String DEV_URL = "http://dev.onepagecrm.com/api/v3/";
    protected static final String STAGING_URL = "http://staging.onepagecrm.com/api/v3/";
    protected static final String ATLAS_URL = "http://atlas.dev.onepagecrm.com/api/v3/";
    protected static final String CALYPSO_URL = "http://calypso.dev.onepagecrm.com/api/v3/";
    protected static final String DEIMOS_URL = "http://deimos.dev.onepagecrm.com/api/v3/";
    protected static final String DRACO_URL = "http://draco.dev.onepagecrm.com/api/v3/";
    protected static final String GEMINI_URL = "http://gemini.dev.onepagecrm.com/api/v3/";
    protected static final String ORION_URL = "http://orion.dev.onepagecrm.com/api/v3/";
    protected static final String PHOBOS_URL = "http://phobos.dev.onepagecrm.com/api/v3/";
    protected static final String SECURE_URL = "https://secure.dev.onepagecrm.com/api/v3/";
    protected static final String TITAN_URL = "http://titan.dev.onepagecrm.com/api/v3/";
    protected static final String VOYAGER_URL = "http://voyager.dev.onepagecrm.com/api/v3/";
    protected static final String LOCAL_DEV_URL = "http://localhost:3000/api/v3/";
    protected static final String NETWORK_DEV_URL = "http://10.100.0.18:3001/api/v3/";

    private static final Map<Integer, String> sUrlMap = new HashMap<>();


    static {
        sUrlMap.put(APP_SERVER, APP_URL);
        sUrlMap.put(DEV_SERVER, DEV_URL);
        sUrlMap.put(STAGING_SERVER, STAGING_URL);
        sUrlMap.put(ATLAS_SERVER, ATLAS_URL);
        sUrlMap.put(CALYPSO_SERVER, CALYPSO_URL);
        sUrlMap.put(DEIMOS_SERVER, DEIMOS_URL);
        sUrlMap.put(DRACO_SERVER, DRACO_URL);
        sUrlMap.put(GEMINI_SERVER, GEMINI_URL);
        sUrlMap.put(ORION_SERVER, ORION_URL);
        sUrlMap.put(PHOBOS_SERVER, PHOBOS_URL);
        sUrlMap.put(SECURE_SERVER, SECURE_URL);
        sUrlMap.put(TITAN_SERVER, TITAN_URL);
        sUrlMap.put(VOYAGER_SERVER, VOYAGER_URL);
        sUrlMap.put(LOCAL_DEV_SERVER, LOCAL_DEV_URL);
        sUrlMap.put(NETWORK_DEV_SERVER, NETWORK_DEV_URL);
    }

    public static String format = ".json";
    protected String endpointUrl;

    protected enum Type {
        GET, POST, PUT, DELETE, PATCH
    }

    protected Type type = Type.GET; // default type GET

    protected static final String GET = "GET";
    protected static final String POST = "POST";
    protected static final String PUT = "PUT";
    protected static final String DELETE = "DELETE";
    protected static final String PATCH = "PATCH";

    protected String requestBody = "";
    protected Map<String, Object> params;
    protected Response response;

    private static final String ACCEPTS_TAG = "Accepts";
    private static final String ACCEPTS = "application/json";

    private static final String CONTENT_TYPE_TAG = "Content-Type";
    private static final String CONTENT_TYPE = "application/json";

    private static final String USER_AGENT_TAG = "User-Agent";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) "
            + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.89 Safari/537.36";

    protected static final String X_UID = "X-OnePageCRM-UID";
    protected static final String X_TS = "X-OnePageCRM-TS";
    protected static final String X_AUTH = "X-OnePageCRM-AUTH";
    protected static final String X_SOURCE = "X-OnePageCRM-SOURCE";

    protected HttpURLConnection connection;

    public abstract void setType();

    public void setEndpointUrl(String endpoint) {
        endpointUrl = sUrlMap.get(OnePageCRM.SERVER) + endpoint + format;
    }

    /**
     * Method require to send HTTP request.
     *
     * @return
     */
    public Response send() {
        boolean mockingRequest = (OnePageCRM.SERVER == MOCK_REQUEST_SERVER);
        if (!mockingRequest) {
            setupAndConnect();
            setRequestMethod();
            setRequestBody();
            setRequestHeaders();
            writeRequestBody();
            getResponse();
            connection.disconnect();
            return response;
        } else {
            return mockRequest();
        }
    }

    private Response mockRequest() {
        Response mockResponse = new Response(0, "OK", "MOCKED REQUEST RESPONSE!");
        LOG.info("*************************************");
        LOG.info("--- REQUEST ---");
        LOG.info("Type: " + type);
        LOG.info("Url: " + getUrl(this.endpointUrl));
        setRequestBody();
        LOG.info("Body: " + requestBody);
        LOG.info("--- RESPONSE ---");
        LOG.info("Code: " + mockResponse.getResponseCode());
        LOG.info("Message: " + mockResponse.getResponseMessage());
        LOG.info("Body: " + mockResponse.getResponseBody());
        LOG.info("*************************************");
        return mockResponse;
    }

    /**
     * Connect to Url using HttpURLConnection class.
     */
    private void setupAndConnect() {
        URL url = getUrl(this.endpointUrl);
        connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
        } catch (IOException e) {
            LOG.severe("Error connecting to Url : " + url);
            LOG.severe(e.toString());
        }
    }

    /**
     * Convert String to Url object.
     *
     * @param url
     * @return
     */
    private URL getUrl(String url) {
        URL requestUrl = null;
        try {
            requestUrl = new URL(url);
        } catch (MalformedURLException e) {
            LOG.severe("Error forming Url for GET request");
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
                try {
                    connection.setDoOutput(true);
                    connection.setRequestMethod(POST);
                } catch (ProtocolException e) {
                    LOG.severe("Could not set request as POST successfully");
                    LOG.severe(e.toString());
                }
                break;
            case PUT:
                try {
                    connection.setDoOutput(true);
                    connection.setRequestMethod(PUT);
                } catch (ProtocolException e) {
                    LOG.severe("Could not set request as PUT successfully");
                    LOG.severe(e.toString());
                }
                break;
            case DELETE:
                try {
                    connection.setDoOutput(true);
                    connection.setRequestMethod(DELETE);
                } catch (ProtocolException e) {
                    LOG.severe("Could not set request as DELETE successfully");
                    LOG.severe(e.toString());
                }
                break;
            case PATCH:
                try {
                    connection.setDoOutput(true);
                    connection.setRequestMethod(PATCH);
                } catch (ProtocolException e) {
                    LOG.severe("Could not set request as PATCH successfully");
                    LOG.severe(e.toString());
                }
                break;
        }
    }

    /**
     * Define the headers for the request. This method will be overridden in
     * SignedRequest to include auth headers.
     */
    public void setRequestHeaders() {
        connection.setRequestProperty(ACCEPTS_TAG, ACCEPTS);
        connection.setRequestProperty(USER_AGENT_TAG, USER_AGENT);
        connection.setRequestProperty(CONTENT_TYPE_TAG, CONTENT_TYPE);

        LOG.info("*************************************");
        LOG.info("--- REQUEST ---");
        LOG.info("Type: " + connection.getRequestMethod());
        LOG.info("Url: " + connection.getURL());
        LOG.info("Body: " + requestBody);
    }

    protected void setRequestBody() {
        if (this.requestBody.equals("")) {
            this.requestBody = BaseSerializer.encodeParams(params);
        }
    }

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
            if (out != null) {
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

        if (scan != null) {
            while (scan.hasNext()) {
                responseBody += scan.nextLine();
            }
            scan.close();
        }
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
