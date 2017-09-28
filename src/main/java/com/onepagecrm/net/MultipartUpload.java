package com.onepagecrm.net;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.net.request.Request;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 27/09/2017.
 */
@SuppressWarnings("PointlessArithmeticExpression")
public class MultipartUpload {

    private static final Logger LOG = Logger.getLogger(MultipartUpload.class.getSimpleName());

    private static final String DEFAULT_USER_AGENT = "Java/1.7.0_80";
    private static final int MAX_BUFFER_SIZE = 1 * 1024 * 1024;
    private static final String TWO_HYPHENS = "--";
    private static final String LINE_END = "\r\n";

    /**
     * Upload a file to a server using multi-part upload.
     */
    public static void perform(String urlTo, Map<String, String> params, String filePath, String fileName, String mimeType) {
        // -------------------------------
        if (OnePageCRM.DEBUG) {
            LOG.info("url: " + urlTo);
            LOG.info("params: " + params);
            LOG.info("filePath: " + filePath);
            LOG.info("fileName: " + fileName);
            LOG.info("mimeType: " + mimeType);
        }
        // -------------------------------

        HttpURLConnection connection;
        DataOutputStream outputStream;
        InputStream inputStream;

        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;

        String[] segments = filePath.split("/");
        int lastIndex = segments.length - 1;
        String nameFromPath = segments[lastIndex]; // TODO: use or remove!?

        try {
            URL url = new URL(urlTo);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(Request.DEFAULT_TIME_OUT_MS);
            HttpURLConnection.setFollowRedirects(true);

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            String userAgentProp = System.getProperty("http.agent");
            String userAgent = Utilities.notNullOrEmpty(userAgentProp) ? userAgentProp : DEFAULT_USER_AGENT;

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", userAgent);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            // -------------------------------
            LOG.info("*************************************");
            LOG.info("--- REQUEST ---");
            LOG.info("Type: " + connection.getRequestMethod());
            LOG.info("Url: " + connection.getURL());
            LOG.info("User-Agent: " + userAgent);
            // -------------------------------

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(TWO_HYPHENS + boundary + LINE_END);

            // Upload POST Data
            for (String key : params.keySet()) {
                String value = params.get(key);
                LOG.info(key + ": " + value);

                outputStream.writeBytes(TWO_HYPHENS + boundary + LINE_END);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + LINE_END);
                outputStream.writeBytes("Content-Type: text/plain" + LINE_END);
                outputStream.writeBytes(LINE_END);
                outputStream.writeBytes(value);
                outputStream.writeBytes(LINE_END);
            }

            outputStream.writeBytes(LINE_END);

            outputStream.writeBytes(TWO_HYPHENS + boundary + LINE_END);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + "file" + "\"; filename=\"" + fileName + "\"" + LINE_END);
            outputStream.writeBytes("Content-Type: " + mimeType + LINE_END);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + LINE_END);
            outputStream.writeBytes(LINE_END);

            // Read from file and write to server (via DataOutputStream).
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
            buffer = new byte[bufferSize];

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            outputStream.writeBytes(LINE_END);

            // Finish communication with server.
            outputStream.writeBytes(TWO_HYPHENS + boundary + TWO_HYPHENS + LINE_END);

            if (connection.getResponseCode() >= 400) {
                inputStream = connection.getErrorStream();
            } else {
                inputStream = connection.getInputStream();
            }
            String result = convertStreamToString(inputStream);

            // -------------------------------
            LOG.info("--- RESPONSE ---");
            LOG.info("Code: " + connection.getResponseCode());
            LOG.info("Message: " + connection.getResponseMessage());
            LOG.info("Body: " + result);
            LOG.info("*************************************");
            // -------------------------------

            // Finish / tidy up.
            fileInputStream.close();
            inputStream.close();
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            LOG.severe("Error in multi-part upload");
            LOG.severe(e.toString());
            e.printStackTrace();
        }
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOG.severe("Error reading from InputStream");
            LOG.severe(e.toString());
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LOG.severe("Error closing InputStream");
                LOG.severe(e.toString());
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
