package com.onepagecrm.models.internal;

import javax.activation.MimetypesFileTypeMap;

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

    public FileReference(String path) {
        initPath(path);
    }

    public FileReference() {
        initPath("");
    }

    private void initPath(String path) {
        this.path = path;
        this.name = FileUtils.nameFromPath(path);
        this.extension = FileUtils.extensionFromName(name);
        this.mimeType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(path);
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
