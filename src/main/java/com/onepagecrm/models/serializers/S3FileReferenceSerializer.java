package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.S3FileReference;
import com.onepagecrm.models.internal.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 27/09/2017.
 */
public class S3FileReferenceSerializer extends BaseSerializer {

    private static Logger LOG = Logger.getLogger(S3FileReferenceSerializer.class.getSimpleName());

    private static S3FileReference DEFAULT = new S3FileReference();

    // TODO: parse errors

    // TODO: add TAGS for strings below

    public static S3FileReference fromString(String responseBody) {
        if (!Utilities.notNullOrEmpty(responseBody)) {
            return DEFAULT;
        }

        try {
            Document document = loadXMLFromString(responseBody);
            document.getDocumentElement().normalize();
            Element rootElement = document.getDocumentElement();

            return new S3FileReference()
                    .setLocation(rootElement.getElementsByTagName("Location").item(0).getTextContent())
                    .setBucket(rootElement.getElementsByTagName("Bucket").item(0).getTextContent())
                    .setKey(rootElement.getElementsByTagName("Key").item(0).getTextContent())
                    .setEtag(rootElement.getElementsByTagName("ETag").item(0).getTextContent());

        } catch (Exception e) {
            LOG.severe("Problems parsing XML.");
            LOG.severe(e.toString());
            e.printStackTrace();
            return DEFAULT;
        }
    }
}
