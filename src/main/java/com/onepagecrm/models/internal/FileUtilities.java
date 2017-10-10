package com.onepagecrm.models.internal;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
     * Get the contents of a file at a given path as a String.
     *
     * @param path - path to file.
     * @return contents of file as {@link String}.
     */
    public static String getResourceContents(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            br.close();
            return sb.toString();

        } catch (IOException e) {
            LOG.severe("Could not read file: " + path);
            LOG.severe(e.toString());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Encodes the byte array into base 64 encoded string.
     *
     * @param imageByteArray - image data as byte[].
     * @return image data as base 64 encoded {@link String}.
     */
    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64String(imageByteArray);
    }

    /**
     * Encodes the image at the given path into base 64 encoded string.
     *
     * @param path - path to file.
     * @return image data as base 64 encoded {@link String}.
     */
    public static String encodeImage(String path) {
        File imageFile = new File(path);

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;

        try {
            // Reading Image file from file system.
            InputStream is = new FileInputStream(imageFile);
            StringBuilder sb = new StringBuilder();

            bytesAvailable = is.available();
            bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
            buffer = new byte[bufferSize];

            bytesRead = is.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                sb.append(Base64.encodeBase64String(buffer));
                bytesAvailable = is.available();
                bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
                bytesRead = is.read(buffer, 0, bufferSize);
            }

            is.close();
            return sb.toString();

        } catch (IOException e) {
            LOG.severe("Problem reading image data.");
            LOG.severe(e.toString());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Decodes the base 64 encode string into byte[] of image data.
     *
     * @param encodedImageData - base 64 encoded {@link String} of image.
     * @return byte[] of image data.
     */
    public static byte[] decodeImage(String encodedImageData) {
        return Base64.decodeBase64(encodedImageData);
    }

    /**
     * Copy from an input stream to an output stream, using an efficiently chosen buffer size.
     *
     * @param is - the {@link InputStream input stream} to be read from.
     * @param os - the {@link OutputStream output stream} to being written to.
     * @return - true if successfully finished copying, false otherwise.
     */
    public static boolean copy(InputStream is, OutputStream os) {
        return copy(is, os, true);
    }

    /**
     * Copy from an input stream to an output stream, using an efficiently chosen buffer size.
     *
     * @param is    - the {@link InputStream input stream} to be read from.
     * @param os    - the {@link OutputStream output stream} to being written to.
     * @param close - whether the streams should be closed/flushed on finish.
     * @return - true if successfully finished copying, false otherwise.
     */
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
