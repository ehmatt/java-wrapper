package com.onepagecrm.models.internal;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.logging.Logger;

@SuppressWarnings("WeakerAccess")
public class Utilities {

    private static final Logger LOG = Logger.getLogger(Utilities.class.getName());
    private static final String NULL = "null";

    /**
     * Method acquires the current Unix-style time.
     * <p/>
     * Unix-style time is the amount of milliseconds elapsed since 01 Jan 1970.
     *
     * @return Unix Time (in milliseconds).
     */
    public static int getUnixTime() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    /* Text Utilities */

    public static boolean notNullOrEmpty(String toBeChecked) {
        return toBeChecked != null && !toBeChecked.equals("");
    }

    public static String nullChecks(String toBeChecked) {
        return NULL.equalsIgnoreCase(toBeChecked) ? null : toBeChecked;
    }

    public static String nullToEmpty(String toBeChecked) {
        return notNullOrEmpty(toBeChecked) ? toBeChecked : "";
    }

    public static String capitalize(String word) {
        if (notNullOrEmpty(word))
            return Character.toString(word.charAt(0)).toUpperCase() + word.substring(1);
        return null;
    }

    public static String capitalizeFully(String word) {
        if (notNullOrEmpty(word))
            return capitalize(word.toLowerCase());
        return null;
    }

    public static String firstCapitalized(String word) {
        if (notNullOrEmpty(word))
            return word.substring(0, 1).toUpperCase();
        return null;
    }

    /**
     * Creates a String made of the input String repeated the desired amount of times.
     *
     * @param toBeRepeated - the String to be repeated.
     * @param times        - the desired amount of times for the input to be repeated.
     * @return The input String repeated 'times' times.
     */
    public static String repeatedString(String toBeRepeated, int times) {
        return CharBuffer.allocate(times).toString().replace("\0", toBeRepeated);
    }

    /**
     * Creates a String made of the input Char repeated the desired amount of times.
     *
     * @param toBeRepeated - the Char to be repeated.
     * @param times        - the desired amount of times for the input to be repeated.
     * @return The input Char repeated 'times' times.
     */
    public static String repeatedChar(char toBeRepeated, int times) {
        return CharBuffer.allocate(times).toString().replace('\0', toBeRepeated);
    }

    /* Specifically for Notes, Calls, Deals */

    public static String bbCodeToHtml(String input) {
        if (notNullOrEmpty(input))
            return input
                    .replace("[b]", "<b>")
                    .replace("[/b]", "</b>")
                    .replace("[i]", "<i>")
                    .replace("[/i]", "</i>");
        return null;
    }

    public static String htmlToBbCode(String input) {
        if (notNullOrEmpty(input))
            return input
                    .replace("<b>", "[b]")
                    .replace("</b>", "[/b]")
                    .replace("<i>", "[i]")
                    .replace("</i>", "[/i]");
        return null;
    }

    public static String stripBbCode(String input) {
        if (notNullOrEmpty(input))
            return input
                    .replace("[b]", "")
                    .replace("[/b]", "")
                    .replace("[i]", "")
                    .replace("[/i]", "");
        return null;
    }

    public static String stripHtml(String input) {
        if (notNullOrEmpty(input))
            return input
                    .replace("<b>", "")
                    .replace("</b>", "")
                    .replace("<i>", "")
                    .replace("</i>", "");
        return null;
    }

    /* File Utilities */

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
            LOG.severe("Could not find file : " + path);
            LOG.severe(e.toString());
        }
        return null;
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
}
