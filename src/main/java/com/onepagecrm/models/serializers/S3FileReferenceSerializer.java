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

    private static final String LOCATION_TAG = "Location";
    private static final String BUCKET_TAG = "Bucket";
    private static final String KEY_TAG = "Key";
    private static final String ETAG_TAG = "ETag";

    private static S3FileReference DEFAULT = new S3FileReference();

    // TODO: parse errors

    // TODO: add TAGS for strings below

    public static S3FileReference fromString(String responseBody) {
        if (!Utilities.notNullOrEmpty(responseBody)) {
            return DEFAULT;
        }

        try {
            Document document = XMLSerializer.documentFromString(responseBody);
            document.getDocumentElement().normalize();
            Element rootElement = document.getDocumentElement();

            return new S3FileReference()
                    .setLocation(XMLSerializer.stringFromElement(rootElement, LOCATION_TAG))
                    .setBucket(XMLSerializer.stringFromElement(rootElement, BUCKET_TAG))
                    .setKey(XMLSerializer.stringFromElement(rootElement, KEY_TAG))
                    .setEtag(XMLSerializer.stringFromElement(rootElement, ETAG_TAG));

        } catch (Exception e) {
            // No need to print... already done in XMLSerializer class.
            return DEFAULT;
        }
    }
}
