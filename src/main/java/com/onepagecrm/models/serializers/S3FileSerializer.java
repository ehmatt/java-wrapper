package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.S3File;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 27/09/2017.
 */
public class S3FileSerializer extends BaseSerializer {

    private static Logger LOG = Logger.getLogger(S3FileSerializer.class.getSimpleName());

    private static S3File DEFAULT = new S3File();

//    public static S3File fromResponse(Response response) throws OnePageException {
//        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
//        return fromJsonObject(dataObject);
//    }

    public static S3File fromString(String responseBody) throws OnePageException {
        String dataString = (String) BaseSerializer.fromString(responseBody);
        try {
            JSONObject dataObject = new JSONObject(dataString);
            return fromJsonObject(dataObject);

        } catch (JSONException e) {
            LOG.severe("Error with JSON");
            LOG.severe(e.toString());
            return DEFAULT;
        }
    }

    public static S3File fromJsonObject(JSONObject dataObject) {
        if (dataObject == null) {
            return DEFAULT;
        }

        JSONObject fieldsObject = dataObject.optJSONObject(FIELDS_TAG);
        if (fieldsObject == null) fieldsObject = new JSONObject();

        return new S3File()
                .setQuota(dataObject.optLong(QUOTA_TAG))
                .setDisplayQuota(dataObject.optString(DISPLAY_QUOTA_TAG))
                .setUrl(dataObject.optString(URL_TAG))
                .setKey(fieldsObject.optString(KEY_TAG))
                .setSuccessStatus(fieldsObject.optInt(SUCCESS_ACTION_STATUS_TAG))
                .setAcl(fieldsObject.optString(ACL_TAG))
                .setPolicy(fieldsObject.optString(POLICY_TAG))
                .setxIgnorePattern(fieldsObject.optString(X_IGNORE_PATTERN_TAG))
                .setxAmzAlgorithm(fieldsObject.optString(X_AMZ_ALGORITHM_TAG))
                .setxAmzCredential(fieldsObject.optString(X_AMZ_CREDENTIAL_TAG))
                .setxAmzDate(fieldsObject.optString(X_AMZ_DATE_TAG))
                .setxAmzSignature(fieldsObject.optString(X_AMZ_SIGNATURE_TAG));
    }

    public static JSONObject toJsonObject(S3File s3File) {
        JSONObject s3FileObject = new JSONObject();
        if (s3File == null) return s3FileObject;
        addJsonLongValue(s3File.getQuota(), s3FileObject, QUOTA_TAG);
        addJsonStringValue(s3File.getDisplayQuota(), s3FileObject, DISPLAY_QUOTA_TAG);
        addJsonStringValue(s3File.getUrl(), s3FileObject, URL_TAG);
        JSONObject fieldsObject = new JSONObject();
        addJsonStringValue(s3File.getKey(), fieldsObject, KEY_TAG);
        addJsonIntegerValue(s3File.getSuccessStatus(), fieldsObject, SUCCESS_ACTION_STATUS_TAG);
        addJsonStringValue(s3File.getAcl(), fieldsObject, ACL_TAG);
        addJsonStringValue(s3File.getPolicy(), fieldsObject, POLICY_TAG);
        addJsonStringValue(s3File.getxIgnorePattern(), fieldsObject, X_IGNORE_PATTERN_TAG);
        addJsonStringValue(s3File.getxAmzAlgorithm(), fieldsObject, X_AMZ_ALGORITHM_TAG);
        addJsonStringValue(s3File.getxAmzCredential(), fieldsObject, X_AMZ_CREDENTIAL_TAG);
        addJsonStringValue(s3File.getxAmzDate(), fieldsObject, X_AMZ_DATE_TAG);
        addJsonStringValue(s3File.getxAmzSignature(), fieldsObject, X_AMZ_SIGNATURE_TAG);
        addJsonObject(fieldsObject, s3FileObject, FIELDS_TAG);
        return s3FileObject;
    }

    public static String toJsonString(S3File s3File) {
        return toJsonObject(s3File).toString();
    }
}
