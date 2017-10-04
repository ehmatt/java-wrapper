package com.onepagecrm.models.internal;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializers.S3DataSerializer;
import com.onepagecrm.net.MultipartUpload;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.Request;

import java.util.Map;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 28/09/2017.
 */
@SuppressWarnings("WeakerAccess")
public class S3 {

    private static final String S3_FORM_ENDPOINT = "attachments/s3_form";

    public static S3Form form(String contactId) throws OnePageException {
        String query = "?" + "contact_id" + "=" + contactId;
        Request request = new GetRequest(S3_FORM_ENDPOINT, query);
        Response response = request.send();
        String responseBody = response.getResponseBody();
        S3Data data = S3DataSerializer.fromString(responseBody);
        return new S3Form().setData(data);
    }

    public static S3FileReference upload(String contactId, S3Form form) throws OnePageException {
        return upload(contactId, form.getData(), form.getFileReference());
    }

    public static S3FileReference upload(String contactId, S3Data data, FileReference fileReference) throws OnePageException {
        Map<String, String> params = S3DataSerializer.toParamMap(contactId, data, fileReference);
        return MultipartUpload.perform(data, params, fileReference);
    }
}
