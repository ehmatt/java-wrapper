package com.onepagecrm.models;

import com.onepagecrm.BaseTest;
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
        String imagePath = "src/test/res/cillian.jpg";
        String correctAnswerPath = "src/test/res/cillian.jpg.base64.txt";

        String base64EncodedImageString = Utilities.encodeImage(imagePath);
        String correctAnswer = Utilities.getResourceContents(correctAnswerPath);

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
        String imagePath = "src/test/res/cillian.jpg";
        String correctAnswerPath = "src/test/res/cillian.jpg.base64.txt";

        File imageFile = new File(imagePath);

        try {
            // Reading Image file from file system.
            FileInputStream imageInFile = new FileInputStream(imageFile);
            byte[] imageData = new byte[(int) imageFile.length()];
            imageInFile.read(imageData);
            imageInFile.close();

            // Converting Image byte array to Base64 encoded String.
            String base64EncodedImageString = Utilities.encodeImage(imageData);
            String correctAnswer = Utilities.getResourceContents(correctAnswerPath);

            // Since Utilities.getResourceContents() adds new line character.
            base64EncodedImageString += "\n";

            assertEquals("Base 64 encoded image strings did not match.",
                    base64EncodedImageString,
                    correctAnswer
            );

        } catch (FileNotFoundException e) {
            // Not expecting this
        } catch (IOException e) {
            ;// Not expecting this
        }
    }
}
