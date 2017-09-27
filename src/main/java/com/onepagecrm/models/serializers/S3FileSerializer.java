package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.S3File;
import com.onepagecrm.models.internal.Utilities;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
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

    public static Map<String, String> toParamMap(S3File s3File, String contactId, String fileName, String contentType, String fileContents) {
        Map<String, String> paramMap = new LinkedHashMap<>();
        if (s3File == null) return paramMap;

        String key = contactId + "/" + System.currentTimeMillis() + "/" + fileName;
        paramMap.put(KEY_TAG, key);

        int successStatus = s3File.getSuccessStatus() != null ? s3File.getSuccessStatus() : 201;
        paramMap.put(SUCCESS_ACTION_STATUS_TAG, String.valueOf(successStatus));

        paramMap.put(ACL_TAG, s3File.getAcl());

        //paramMap.put("Content-Type", contentType);

        paramMap.put(X_IGNORE_PATTERN_TAG, s3File.getxIgnorePattern());

        paramMap.put(X_AMZ_CREDENTIAL_TAG, s3File.getxAmzCredential());

        paramMap.put(X_AMZ_ALGORITHM_TAG, s3File.getxAmzAlgorithm());

        paramMap.put(X_AMZ_DATE_TAG, s3File.getxAmzDate());

        String policy = s3File.getPolicy();
        byte[] policyBytes = policy.getBytes(StandardCharsets.UTF_8);
        String policyBase64 = Base64.encodeBase64String(policyBytes);
        paramMap.put("Policy", policy);

        paramMap.put(X_AMZ_SIGNATURE_TAG, s3File.getxAmzSignature());

        paramMap.put(FILENAME_TAG, fileName);

        String content = Utilities.notNullOrEmpty(fileContents) ? fileContents : "";
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        String contentBase64 = Base64.encodeBase64String(contentBytes);
        //paramMap.put(FILE_TAG, contentBase64);

        return paramMap;
    }

    public static JSONObject toJsonObject(S3File s3File, String contactId, String fileName, String contentType, String fileContents) {
        JSONObject s3FileObject = new JSONObject();
        if (s3File == null) return s3FileObject;
        String key = contactId + "/" + Utilities.getUnixTime() + "/" + fileName;
        addJsonStringValue(key, s3FileObject, KEY_TAG);
        addJsonIntegerValue(s3File.getSuccessStatus(), s3FileObject, SUCCESS_ACTION_STATUS_TAG);
        addJsonStringValue(contentType, s3FileObject, "Content-Type");
        addJsonStringValue(s3File.getAcl(), s3FileObject, ACL_TAG);
        String policy = s3File.getPolicy();
        byte[] policyBytes = policy.getBytes(StandardCharsets.UTF_8);
        String policyBase64 = Base64.encodeBase64String(policyBytes);
        addJsonStringValue(policyBase64, s3FileObject, "Policy");
        addJsonStringValue(s3File.getxIgnorePattern(), s3FileObject, X_IGNORE_PATTERN_TAG);
        addJsonStringValue(s3File.getxAmzAlgorithm(), s3FileObject, X_AMZ_ALGORITHM_TAG);
        addJsonStringValue(s3File.getxAmzCredential(), s3FileObject, X_AMZ_CREDENTIAL_TAG);
        addJsonStringValue(s3File.getxAmzDate(), s3FileObject, X_AMZ_DATE_TAG);
        addJsonStringValue(s3File.getxAmzSignature(), s3FileObject, X_AMZ_SIGNATURE_TAG);
        addJsonStringValue(fileName, s3FileObject, FILENAME_TAG);
        String content = Utilities.notNullOrEmpty(fileContents) ? fileContents : "";
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        String contentBase64 = Base64.encodeBase64String(contentBytes);
        addJsonStringValue(contentBase64, s3FileObject, FILE_TAG);
        return s3FileObject;
    }

    public static String toJsonString(S3File s3File, String contactId, String filename, String contentType, String fileContents) {
        return toJsonObject(s3File, contactId, filename, contentType, fileContents).toString();
    }

    public static JSONObject toJsonObjectFull(S3File s3File) {
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

    public static String toJsonStringFull(S3File s3File) {
        return toJsonObjectFull(s3File).toString();
    }
}
