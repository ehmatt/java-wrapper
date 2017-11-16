package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.internal.S3FileReference;
import com.onepagecrm.models.internal.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 27/09/2017.
 */
@SuppressWarnings("unused")
public class S3FileReferenceSerializer extends XMLSerializer {

    private static Logger LOG = Logger.getLogger(S3FileReferenceSerializer.class.getSimpleName());

    private static S3FileReference DEFAULT = new S3FileReference();

    public static S3FileReference fromString(String responseBody) throws APIException {
        if (!Utilities.notNullOrEmpty(responseBody)) {
            return DEFAULT;
        }

        Document document = XMLSerializer.documentFromString(responseBody);
        Element rootElement = XMLSerializer.rootElementFromDoc(document);

        return new S3FileReference()
                .setLocation(XMLSerializer.stringFromElement(rootElement, LOCATION_TAG))
                .setBucket(XMLSerializer.stringFromElement(rootElement, BUCKET_TAG))
                .setKey(XMLSerializer.stringFromElement(rootElement, KEY_TAG))
                .setEtag(XMLSerializer.stringFromElement(rootElement, ETAG_TAG));
    }
}
