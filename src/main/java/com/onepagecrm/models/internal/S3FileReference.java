package com.onepagecrm.models.internal;

import java.io.Serializable;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 28/09/2017.
 */
public class S3FileReference extends FileReference implements Serializable {

    public static final long MAX_SIZE_BYTES = 10 * 1024 * 1024; // 10 MB / 10485760 bytes

    private String location;
    private String bucket;
    private String key;
    private String etag;

    public S3FileReference(FileReference fileReference) {
        super(fileReference);
    }

    public S3FileReference(String path) {
        super(path);
    }

    public S3FileReference() {
        super();
    }

    @Override
    public String toString() {
        return "S3FileReference{" +
                "location='" + location + '\'' +
                ", bucket='" + bucket + '\'' +
                ", key='" + key + '\'' +
                ", etag='" + etag + '\'' +
                ", path='" + getPath() + '\'' +
                ", name='" + getName() + '\'' +
                ", extension='" + getExtension() + '\'' +
                ", mimeType='" + getMimeType() + '\'' +
                ", size=" + getSize() +
                '}';
    }

    @Override
    public boolean isValid() {
        return notNullOrEmpty(key);
    }

    public String getLocation() {
        return location;
    }

    public S3FileReference setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getBucket() {
        return bucket;
    }

    public S3FileReference setBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public String getKey() {
        return key;
    }

    public S3FileReference setKey(String key) {
        this.key = key;
        return this;
    }

    public String getEtag() {
        return etag;
    }

    public S3FileReference setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    @Override
    public S3FileReference setName(String name) {
        super.setName(name);
        return this;
    }

    @Override
    public S3FileReference setExtension(String extension) {
        super.setExtension(extension);
        return this;
    }

    @Override
    public S3FileReference setMimeType(String mimeType) {
        super.setMimeType(mimeType);
        return this;
    }

    @Override
    public S3FileReference setSize(long size) {
        super.setSize(size);
        return this;
    }
}
