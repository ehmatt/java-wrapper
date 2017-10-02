package com.onepagecrm.models.serializers;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.internal.S3FileReference;
import com.onepagecrm.models.internal.Utilities;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 02/10/2017.
 */
public class AttachmentSerializerTest extends BaseTest {

    private static Logger LOG = Logger.getLogger(AttachmentSerializerTest.class.getName());

    private static final String HARDCODED_FILENAME = "cillian.jpg";
    private static final String HARDCODED_LOCATION = "https://s3-us-west-1.amazonaws.com/onepagecrm-ud2-us-west-1/56fa81eb9007ba07fc000080%2F1506589944670%2Fcillian.jpg";
    private static final String HARDCODED_BUCKET = "onepagecrm-ud2-us-west-1";
    private static final String HARDCODED_KEY = "56fa81eb9007ba07fc000080/1506589944670/cillian.jpg";
    private static final String HARDCODED_ETAG = "\"b771fbbc72899aecedaf3aa77fcce2c1\"";

    private S3FileReference expected;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        expected = new S3FileReference()
                .setName(HARDCODED_FILENAME)
                .setLocation(HARDCODED_LOCATION)
                .setBucket(HARDCODED_BUCKET)
                .setKey(HARDCODED_KEY)
                .setEtag(HARDCODED_ETAG);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testS3XmlSerialization() throws Exception {
        String filePath = "./src/test/res/responses/xml/s3_upload_success.xml";
        String successXml = Utilities.getResourceContents(filePath);
        S3FileReference actual = S3Serializer.fromXml(successXml);

        // Make sure both are non-null and valid s3 file refs.
        assertTrue("Expected must be non-null and valid s3 file ref.",
                expected != null && expected.isValid());
        assertTrue("Actual must be non-null and valid s3 file ref.",
                actual != null && actual.isValid());

        assertEquals("Locations must be equal", expected.getLocation(), actual.getLocation());
        assertEquals("Buckets must be equal", expected.getBucket(), actual.getBucket());
        assertEquals("Keys must be equal", expected.getKey(), actual.getKey());
        assertEquals("ETags must be equal", expected.getEtag(), actual.getEtag());
    }
}
