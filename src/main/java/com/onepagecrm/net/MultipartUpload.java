package com.onepagecrm.net;

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
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 27/09/2017.
 */
public class MultipartUpload {

    private static final Logger LOG = Logger.getLogger(MultipartUpload.class.getSimpleName());

    public static String perform(String urlTo, Map<String, String> params, String filePath, String fileName, String mimeType) {
        // -------------------------------
        //LOG.info("url: " + urlTo);
        //LOG.info("params: " + params);
        //LOG.info("filePath: " + filePath);
        //LOG.info("fileName: " + fileName);
        //LOG.info("mimeType: " + mimeType);
        // -------------------------------

        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        InputStream inputStream = null;

        String twoHyphens = "--";
        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
        String lineEnd = "\r\n";

        String result = "";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        String[] q = filePath.split("/");
        int idx = q.length - 1;

        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            URL url = new URL(urlTo);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(Request.DEFAULT_TIME_OUT_MS);
            HttpURLConnection.setFollowRedirects(true);

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            //String userAgent = System.getProperty("http.agent");
            String userAgent = "Java/1.7.0_80";
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
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);

            // Upload POST Data
            Iterator<String> keys = params.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = params.get(key);
                LOG.info(key + ": " + value);

                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(value);
                outputStream.writeBytes(lineEnd);
            }

            outputStream.writeBytes(lineEnd);

            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + "file" + "\"; filename=\"" + fileName + "\"" + lineEnd);
            outputStream.writeBytes("Content-Type: " + mimeType + lineEnd);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);
            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnd);

            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // -------------------------------
            LOG.info("--- RESPONSE ---");
            LOG.info("Code: " + connection.getResponseCode());
            LOG.info("Message: " + connection.getResponseMessage());
            // -------------------------------

            if (connection.getResponseCode() >= 400) {
                inputStream = connection.getErrorStream();
            } else {
                inputStream = connection.getInputStream();
            }

            result = convertStreamToString(inputStream);

            // -------------------------------
            LOG.info("Body: " + result);
            LOG.info("*************************************");
            // -------------------------------

            fileInputStream.close();
            inputStream.close();
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            LOG.severe("Error in multi-part upload");
            LOG.severe(e.toString());
            e.printStackTrace();
        }

        return result;
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
