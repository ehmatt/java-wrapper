package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.models.serializers.AttachmentSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.PutRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.Date;

public class Attachment extends ApiResource implements Serializable {

    /**
     * Constants.
     */

    private static final String PROVIDER_AMAZON = "amazon";
    private static final String PROVIDER_DRIVE = "google_drive";
    private static final String PROVIDER_DROPBOX = "dropbox";
    private static final String PROVIDER_OTHER = "other";

    /**
     * Member variables.
     */

    public enum Provider {
        AMAZON(PROVIDER_AMAZON),
        DRIVE(PROVIDER_DRIVE),
        DROPBOX(PROVIDER_DROPBOX),
        OTHER(PROVIDER_OTHER);

        private String provider;

        Provider(String provider) {
            this.provider = provider;
        }

        @Override
        public String toString() {
            return provider;
        }

        public static Provider fromString(String provider) {
            if (provider == null) return null;
            switch (provider) {
                case PROVIDER_AMAZON:
                    return AMAZON;
                case PROVIDER_DRIVE:
                    return DRIVE;
                case PROVIDER_DROPBOX:
                    return DROPBOX;
                default:
                    return OTHER;
            }
        }
    }

    private String id;
    private String filename;
    private Provider provider;
    private String url;
    private Long size;
    private Date expiresAt;

    /**
     * API methods
     */

    public Attachment save() throws OnePageException {
        return isValid() ? update() : create();
    }

    private Attachment update() throws OnePageException {
        Request request = new PutRequest(
                addIdToEndpoint(ATTACHMENTS_ENDPOINT),
                null,
                AttachmentSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return AttachmentSerializer.fromString(response.getResponseBody());
    }

    private Attachment create() throws OnePageException {
        Request request = new PostRequest(
                ATTACHMENTS_ENDPOINT,
                null,
                AttachmentSerializer.toJsonObject(this));
        Response response = request.send();
        return AttachmentSerializer.fromString(response.getResponseBody());
    }

    private String addIdToEndpoint(String endpoint) {
        return endpoint + "/" + this.id;
    }

    /**
     * Utility methods
     */

    public String getFileExtension() {
        return Utilities.notNullOrEmpty(filename) && filename.contains(".") ?
                filename.substring(filename.lastIndexOf(".")) : "";
    }

    /**
     * Object methods
     */

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Attachment setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return AttachmentSerializer.toJsonObject(this);
    }

    public String getFilename() {
        return filename;
    }

    public Attachment setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public Provider getProvider() {
        return provider;
    }

    public Attachment setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Attachment setUrl(String url) {
        this.url = url;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public Attachment setSize(Long size) {
        this.size = size;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public Attachment setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }
}
