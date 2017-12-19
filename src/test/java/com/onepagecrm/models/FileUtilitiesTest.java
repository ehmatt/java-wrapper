package com.onepagecrm.models;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.internal.FileUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SuppressWarnings({"ResultOfMethodCallIgnored", "RedundantThrows"})
public class FileUtilitiesTest extends BaseTest {

    /**
     * Should generate the same String as the contents of the text file.
     */
    public void testEncodeImage_FromPath() {
        String imagePath = "src/test/res/image_encode/cillian.jpg";
        String correctAnswerPath = "src/test/res/image_encode/cillian.jpg.base64.dat";

        String base64EncodedImageString = FileUtilities.encodeImage(imagePath);
        String correctAnswer = FileUtilities.getResourceContents(correctAnswerPath);

        // Since Utilities.getResourceContents() adds new line character.
        base64EncodedImageString += "\n";

        assertEquals("Base 64 encoded image strings did not match.",
                base64EncodedImageString,
                correctAnswer
        );
    }

    /**
     * Should generate the same String as the contents of the text file.
     */
    public void testEncodeImage_FromBytes() {
        String imagePath = "src/test/res/image_encode/cillian.jpg";
        String correctAnswerPath = "src/test/res/image_encode/cillian.jpg.base64.dat";

        File imageFile = new File(imagePath);

        try {
            // Reading Image file from file system.
            FileInputStream imageInFile = new FileInputStream(imageFile);
            byte[] imageData = new byte[(int) imageFile.length()];
            imageInFile.read(imageData);
            imageInFile.close();

            // Converting Image byte array to Base64 encoded String.
            String base64EncodedImageString = FileUtilities.encodeImage(imageData);
            String correctAnswer = FileUtilities.getResourceContents(correctAnswerPath);

            // Since Utilities.getResourceContents() adds new line character.
            base64EncodedImageString += "\n";

            assertEquals("Base 64 encoded image strings did not match.",
                    base64EncodedImageString,
                    correctAnswer
            );

        } catch (IOException e) {
            // Not expecting this
        }
    }

    public void testBytesToKb() throws Exception {
        final long expected = 1L; // 1 kB
        final long bytes = 1024; // 1024 bytes

        assertEquals("Byte conversion failed.",
                expected, FileUtilities.bytesToKb(bytes));
    }

    public void testBytesToMb() throws Exception {
        final long expected = 1L; // 1 MB
        final long bytes = 1024 * 1024; // 1048576 bytes

        assertEquals("Byte conversion failed.",
                expected, FileUtilities.bytesToMb(bytes));
    }

    public void testKbToBytes() throws Exception {
        final long expected = 1024; // 1024 bytes
        final long kiloBytes = 1L; // 1 kB

        assertEquals("Byte conversion failed.",
                expected, FileUtilities.kbToBytes(kiloBytes));
    }

    public void testMbToBytes() throws Exception {
        final long expected = 1024 * 1024; // 1048576 bytes
        final long megaBytes = 1L; // 1 MB

        assertEquals("Byte conversion failed.",
                expected, FileUtilities.mbToBytes(megaBytes));
    }
}
