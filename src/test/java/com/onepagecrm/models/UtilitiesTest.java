package com.onepagecrm.models;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.internal.FileUtilities;
import com.onepagecrm.models.internal.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilitiesTest extends BaseTest {

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

        } catch (FileNotFoundException e) {
            // Not expecting this
        } catch (IOException e) {
            // Not expecting this
        }
    }

    public void testRepeatedString() throws Exception {
        String toBeRepeated = "hi";
        int times = 3;

        assertEquals("hihihi", Utilities.repeatedString(toBeRepeated, times));
    }

    public void testRepeatedChar() throws Exception {
        char toBeRepeated = 'x';
        int times = 5;

        assertEquals("xxxxx", Utilities.repeatedChar(toBeRepeated, times));
    }
}
