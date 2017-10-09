package com.onepagecrm.models.internal;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 09/10/2017.
 */
@SuppressWarnings({"UnusedReturnValue", "unused", "WeakerAccess"})
public class FileUtilities {

    private static final Logger LOG = Logger.getLogger(FileUtilities.class.getName());

    private static final int MAX_BUFFER_SIZE = 1024 * 1024; // 1 MB

    /**
     * Get the String file contents at a given path.
     *
     * @param path - String
     * @return
     */
    public static String getResourceContents(String path) {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                return sb.toString();
            }

        } catch (IOException e) {
            LOG.severe("Could not find file: " + path);
            LOG.severe(e.toString());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64String(imageByteArray);
    }

    /**
     * Encodes the byte array into base64 String
     *
     * @param pathToImage - String
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(String pathToImage) {
        File imageFile = new File(pathToImage);

        try {
            // Reading Image file from file system.
            FileInputStream imageInFile = new FileInputStream(imageFile);
            byte[] imageData = new byte[(int) imageFile.length()];
            imageInFile.read(imageData);
            String imageDataString = Base64.encodeBase64String(imageData);
            imageInFile.close();
            return imageDataString;

        } catch (FileNotFoundException e) {
            LOG.severe("Image not found\n" + e);
        } catch (IOException e) {
            LOG.severe("Exception while reading the Image\n" + e);
        }

        return null;
    }

    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }

    public static boolean copy(InputStream is, OutputStream os) {
        return copy(is, os, true);
    }

    public static boolean copy(InputStream is, OutputStream os, boolean close) {
        if (is == null || os == null) return false;

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;

        try {
            bytesAvailable = is.available();
            bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
            buffer = new byte[bufferSize];

            bytesRead = is.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                os.write(buffer, 0, bufferSize);
                bytesAvailable = is.available();
                bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
                bytesRead = is.read(buffer, 0, bufferSize);
            }

            if (close) {
                is.close();
                os.flush();
                os.close();
            }
            return true;

        } catch (IOException e) {
            LOG.severe("Problem copying from input stream to output stream.");
            LOG.severe(e.toString());
            e.printStackTrace();
            return false;
        }
    }
}
