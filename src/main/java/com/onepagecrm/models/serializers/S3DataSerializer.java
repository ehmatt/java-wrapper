package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Attachment;
import com.onepagecrm.models.internal.FileReference;
import com.onepagecrm.models.internal.S3Data;
import com.onepagecrm.models.internal.S3FileReference;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 27/09/2017.
 */
public class S3DataSerializer extends BaseSerializer {

    private static Logger LOG = Logger.getLogger(S3DataSerializer.class.getSimpleName());

    private static S3Data DEFAULT = new S3Data();

//    public static S3Data fromResponse(Response response) throws OnePageException {
//        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
//        return fromJsonObject(dataObject);
//    }

    public static S3Data fromString(String responseBody) throws OnePageException {
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

    public static S3Data fromJsonObject(JSONObject dataObject) {
        if (dataObject == null) {
            return DEFAULT;
        }

        JSONObject fieldsObject = dataObject.optJSONObject(FIELDS_TAG);
        if (fieldsObject == null) fieldsObject = new JSONObject();

        return new S3Data()
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

    public static Map<String, String> toParamMap(S3Data data, String contactId, FileReference fileReference) {
        Map<String, String> paramMap = new LinkedHashMap<>();
        if (data == null) return paramMap;
        String key = contactId + "/" + System.currentTimeMillis() + "/" + fileReference.getName();
        paramMap.put(KEY_TAG, key);
        int successStatus = data.getSuccessStatus() != null ? data.getSuccessStatus() : 201;
        paramMap.put(SUCCESS_ACTION_STATUS_TAG, String.valueOf(successStatus));
        paramMap.put(ACL_TAG, data.getAcl());
        paramMap.put(X_IGNORE_PATTERN_TAG, data.getxIgnorePattern());
        paramMap.put(X_AMZ_CREDENTIAL_TAG, data.getxAmzCredential());
        paramMap.put(X_AMZ_ALGORITHM_TAG, data.getxAmzAlgorithm());
        paramMap.put(X_AMZ_DATE_TAG, data.getxAmzDate());
        paramMap.put(POLICY_TAG, data.getPolicy());
        paramMap.put(X_AMZ_SIGNATURE_TAG, data.getxAmzSignature());
        paramMap.put(FILENAME_TAG, fileReference.getName());
        return paramMap;
    }

    public static JSONObject toJsonObject(S3Data s3Data) {
        JSONObject s3FileObject = new JSONObject();
        if (s3Data == null) return s3FileObject;
        addJsonLongValue(s3Data.getQuota(), s3FileObject, QUOTA_TAG);
        addJsonStringValue(s3Data.getDisplayQuota(), s3FileObject, DISPLAY_QUOTA_TAG);
        addJsonStringValue(s3Data.getUrl(), s3FileObject, URL_TAG);
        JSONObject fieldsObject = new JSONObject();
        addJsonStringValue(s3Data.getKey(), fieldsObject, KEY_TAG);
        addJsonIntegerValue(s3Data.getSuccessStatus(), fieldsObject, SUCCESS_ACTION_STATUS_TAG);
        addJsonStringValue(s3Data.getAcl(), fieldsObject, ACL_TAG);
        addJsonStringValue(s3Data.getPolicy(), fieldsObject, POLICY_TAG);
        addJsonStringValue(s3Data.getxIgnorePattern(), fieldsObject, X_IGNORE_PATTERN_TAG);
        addJsonStringValue(s3Data.getxAmzAlgorithm(), fieldsObject, X_AMZ_ALGORITHM_TAG);
        addJsonStringValue(s3Data.getxAmzCredential(), fieldsObject, X_AMZ_CREDENTIAL_TAG);
        addJsonStringValue(s3Data.getxAmzDate(), fieldsObject, X_AMZ_DATE_TAG);
        addJsonStringValue(s3Data.getxAmzSignature(), fieldsObject, X_AMZ_SIGNATURE_TAG);
        addJsonObject(fieldsObject, s3FileObject, FIELDS_TAG);
        return s3FileObject;
    }

    public static String toJsonString(S3Data s3Data) {
        return toJsonObject(s3Data).toString();
    }

    public static JSONObject toJsonObject(S3FileReference file, Attachment attachment, String contactId) {
        JSONObject jsonObject = new JSONObject();
        if (file == null || attachment == null || contactId == null) return jsonObject;
        addJsonStringValue(contactId, jsonObject, CONTACT_ID_TAG);
        addJsonStringValue(attachment.getReferenceId(), jsonObject, REFERENCE_ID_TAG);
        if (attachment.getReferenceType() != null) {
            addJsonStringValue(attachment.getReferenceType().toString(), jsonObject, REFERENCE_TYPE_TAG);
        }
        addJsonStringValue(file.getName(), jsonObject, NAME_TAG);
        addJsonStringValue(file.getKey(), jsonObject, KEY_TAG);
        addJsonLongValue(file.getSize(), jsonObject, SIZE_TAG);
        // TODO: provider!?
        if (attachment.getProvider() != null) {
            switch (attachment.getProvider()) {
                case DRIVE:
                case DROPBOX:
                case EVERNOTE: {
                    addJsonStringValue(attachment.getExternalUrl(), jsonObject, EXTERNAL_URL_TAG);
                    addJsonStringValue(attachment.getProvider().toString(), jsonObject, LINK_TYPE_TAG);
                    break;
                }
                default: {
                    //addNullValue(jsonObject, EXTERNAL_URL_TAG); // TODO: remove??
                    //addNullValue(jsonObject, LINK_TYPE_TAG); // TODO: remove??
                    break;
                }
            }
        }
        return jsonObject;
    }

    public static String toJsonString(S3FileReference file, Attachment attachment, String contactId) {
        return toJsonObject(file, attachment, contactId).toString();
    }
}
