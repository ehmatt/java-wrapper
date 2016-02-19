package com.onepagecrm.models.internal;

import com.onepagecrm.OnePageCRM;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Utilities {

    private static final Logger LOG = Logger.getLogger(Utilities.class.getName());

//    /**
//     * Get the String file contents at a given path.
//     *
//     * @param name - String
//     * @return
//     */
//    public static String getResourceContentsFor(String name) {
//        try {
//            ClassLoader classLoader = OnePageCRM.class.getClassLoader();
//            LOG.info("classLoader : " + classLoader);
//            LOG.info("classLoader.getClass() : " + classLoader.getClass());
//            LOG.info("classLoader.getParent() : " + classLoader.getParent());
////            InputStream inputStream = classLoader.getResourceAsStream("/src/test/res/responses/perfect/" + name);
////            LOG.info("inputStream : " + inputStream);
//
//            URL resource = OnePageCRM.class.getResource("../../../../../../src/test/res/responses/perfect/" + name);
//            LOG.info("resource : " + resource);
//            File file = Paths.get(resource.toURI()).toFile();
//            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                StringBuilder sb = new StringBuilder();
//                String line = br.readLine();
//
//                while (line != null) {
//                    sb.append(line);
//                    sb.append(System.lineSeparator());
//                    line = br.readLine();
//                }
//                return sb.toString();
//            }
//        } catch (IOException e) {
//            LOG.severe("Could not find file : " + name);
//            LOG.severe(e.toString());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    /**
//     * Get the String file contents at a given path.
//     *
//     * @param fileName - String
//     * @return
//     */
//    public static String getResourceContentsFor(String fileName) {
//        StringBuilder result = new StringBuilder();
//        ClassLoader classLoader = OnePageCRM.class.getClassLoader();
//        File file = new File(classLoader.getResource("../../../../test/res/responses/perfect/" + fileName).getFile());
//        try (Scanner scanner = new Scanner(file)) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                result.append(line).append("\n");
//            }
//            scanner.close();
//        } catch (IOException e) {
//            LOG.severe("Could not find file : " + fileName);
//            LOG.severe(e.toString());
//        }
//        return result.toString();
//    }

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
