package com.onepagecrm.samples;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Attachment;
import com.onepagecrm.models.User;
import com.onepagecrm.models.internal.FileReference;
import com.onepagecrm.models.internal.S3;
import com.onepagecrm.models.internal.S3Data;
import com.onepagecrm.models.internal.S3FileReference;
import com.onepagecrm.models.internal.S3Form;
import com.onepagecrm.models.serializers.S3DataSerializer;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static com.onepagecrm.net.ApiResource.ATTACHMENTS_ENDPOINT;

public class AttachmentsPh2Driver {

    private static final Logger LOG = Logger.getLogger(AttachmentsPh2Driver.class.getName());

    public static void main(String[] args) throws OnePageException {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // Load the properties file
            prop.load(input);

        } catch (IOException e) {
            LOG.severe("Error loading the config.properties file");
            LOG.severe(e.toString());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.severe("Error closing the config.properties file");
                    LOG.severe(e.toString());
                }
            }
        }

        OnePageCRM.setServer(Request.DEV_SERVER);

        User loggedInUser = User.login(
                prop.getProperty("username"),
                prop.getProperty("password"));

        LOG.info("Logged in User : " + loggedInUser);

        String contactId = "56fa81eb9007ba07fc000080";
        S3Form form = S3.form(contactId);
        S3Data data = form.getData();

        LOG.info("S3 data : " + data);

        //String endpoint = s3File.getUrl();
        //OnePageCRM.setCustomUrl(endpoint);
        //OnePageCRM.setServer(Request.CUSTOM_URL_SERVER);
        //Request.format = "";

        String fileName = "cillian.jpg";
        String contentType = "image/jpeg";
        String filePath = "./src/test/res/image_encode/" + fileName;
        FileReference file = new FileReference(filePath).setMimeType(contentType);
        LOG.info("" + file.toString());

        // OPTION 1: form
        //form.setFileReference(file);
        //S3FileReference uploaded = S3.upload(contactId, form);

        // OPTION 2: data + file
        //S3FileReference uploaded = S3.upload(contactId, data, file);

        // OPTION 3: fake
        String hardcodedKey = "56fa81eb9007ba07fc000080/1506589944670/cillian.jpg";
        long hardcodedSize = 31528L;
        S3FileReference uploaded = new S3FileReference(file)
                .setKey(hardcodedKey)
                .setSize(hardcodedSize);

        LOG.info("" + uploaded.toString());

        Attachment attachment = new Attachment()
                .setId(null)
                .setProvider(Attachment.Provider.AMAZON)
                .setFilename(fileName)
                .setReferenceId("59cccf4b9007ba0db50775a0")
                .setReferenceType(Attachment.ReferenceType.DEAL);

        String body = S3DataSerializer.toJsonString(uploaded, attachment, contactId);
        Request referenceRequest = new PostRequest(ATTACHMENTS_ENDPOINT, "", body);
        referenceRequest.send();
    }
}
