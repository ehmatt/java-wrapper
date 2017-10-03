package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Attachment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 31/07/2017.
 */
public class AttachmentSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(AttachmentSerializer.class.getName());

    public static Attachment fromString(String responseBody) throws OnePageException {
        String parsedResponse;
        OnePageException exception;
        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            return fromJsonObject(responseObject);

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;
        } catch (JSONException e) {
            LOG.severe("Could not find attachment object tags in response");
            LOG.severe(e.toString());
            return new Attachment();
        }
    }

    public static Attachment fromJsonObject(JSONObject attachmentObject) {
        if (attachmentObject == null) {
            return null;
        }
        if (attachmentObject.has(ATTACHMENT_TAG)) {
            attachmentObject = attachmentObject.optJSONObject(ATTACHMENT_TAG);
        }
        return new Attachment()
                .setId(attachmentObject.optString(ID_TAG))
                .setFilename(attachmentObject.optString(FILENAME_TAG))
                .setUrl(attachmentObject.optString(URL_TAG))
                .setSize(attachmentObject.optLong(SIZE_TAG))
                .setProvider(Attachment.Provider.fromString(attachmentObject.optString(STORAGE_PROVIDER_TAG)))
                .setExpiresAt(DateSerializer.fromFormattedString(attachmentObject.optString(URL_EXPIRES_AT_TAG)));
    }

    public static String toJsonObject(Attachment attachment) {
        JSONObject attachmentObject = new JSONObject();
        if (attachment == null) return attachmentObject.toString();
        addJsonStringValue(attachment.getId(), attachmentObject, ID_TAG);
        addJsonStringValue(attachment.getFilename(), attachmentObject, FILENAME_TAG);
        addJsonStringValue(attachment.getUrl(), attachmentObject, URL_TAG);
        addJsonLongValue(attachment.getSize(), attachmentObject, SIZE_TAG);
        addJsonObjectValue(attachment.getProvider(), attachmentObject, STORAGE_PROVIDER_TAG);
        addJsonStringValue(
                DateSerializer.toFormattedDateTimeString(attachment.getExpiresAt()),
                attachmentObject,
                URL_EXPIRES_AT_TAG
        );
        addJsonObjectValue(attachment.getReferenceType(), attachmentObject, REFERENCE_TYPE_TAG);
        addJsonStringValue(attachment.getReferenceId(), attachmentObject, REFERENCE_ID_TAG);
        return attachmentObject.toString();
    }

    public static List<Attachment> fromJsonArray(JSONArray attachmentsArray) {
        List<Attachment> attachments = new ArrayList<>();
        if (attachmentsArray == null) {
            return attachments;
        }
        for (int i = 0; i < attachmentsArray.length(); i++) {
            JSONObject attachmentObject = attachmentsArray.optJSONObject(i);
            Attachment attachment = fromJsonObject(attachmentObject);
            if (attachment != null) {
                attachments.add(attachment);
            }
        }
        return attachments;
    }

    public static String toJsonArray(List<Attachment> attachments) {
        JSONArray attachmentsArray = new JSONArray();
        if (attachments == null || attachments.isEmpty()) {
            return attachmentsArray.toString();
        }
        for (Attachment attachment : attachments) {
            attachmentsArray.put(toJsonObject(attachment));
        }
        return attachmentsArray.toString();
    }
}
