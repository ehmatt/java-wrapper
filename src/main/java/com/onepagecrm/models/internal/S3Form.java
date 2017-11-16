package com.onepagecrm.models.internal;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 28/09/2017.
 */
@SuppressWarnings("WeakerAccess")
public class S3Form {

    private S3Data data;
    private FileReference fileReference;

    public S3Form() {

    }

    public S3Data getData() {
        return data;
    }

    public S3Form setData(S3Data data) {
        this.data = data;
        return this;
    }

    public FileReference getFileReference() {
        return fileReference;
    }

    public S3Form setFileReference(FileReference fileReference) {
        this.fileReference = fileReference;
        return this;
    }
}
