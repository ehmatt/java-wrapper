package com.onepagecrm.models.internal;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 28/09/2017.
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class FileReference {

    private String path;
    private String name;
    private String extension;
    private String mimeType;
    private long size;

    public FileReference(FileReference fileReference) {
        this();
        if (fileReference == null) return;
        this.setPath(fileReference.getPath());
        this.setName(fileReference.getName());
        this.setExtension(fileReference.getExtension());
        this.setMimeType(fileReference.getMimeType());
        this.setSize(fileReference.getSize());
    }

    public FileReference(String path) {
        initPath(path);
    }

    public FileReference() {
        initPath("");
    }

    private void initPath(String path) {
        this.path = path;
        this.name = FileRefUtils.nameFromPath(path);
        this.extension = FileRefUtils.extensionFromName(name);
        this.mimeType = FileRefUtils.mimeTypeFromPath(path);
    }

    @Override
    public String toString() {
        return "FileReference{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", size=" + size +
                '}';
    }

    public boolean isValid() {
        return notNullOrEmpty(path) && notNullOrEmpty(name);
    }

    public String getPath() {
        return path;
    }

    public FileReference setPath(String path) {
        initPath(path);
        return this;
    }

    public String getName() {
        return name;
    }

    public FileReference setName(String name) {
        this.name = name;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public FileReference setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public FileReference setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public long getSize() {
        return size;
    }

    public FileReference setSize(long size) {
        this.size = size;
        return this;
    }
}
