package com.onepagecrm.models.internal;

import javax.activation.MimetypesFileTypeMap;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 28/09/2017.
 */
@SuppressWarnings("WeakerAccess")
public class FileRefUtils {

    public static String nameFromPath(String path) {
        final String empty = "";
        if (!Utilities.notNullOrEmpty(path)) {
            return empty;
        }

        String[] segments = path.split("/");
        int lastIndex = segments.length - 1;
        return lastIndex >= 0 ? segments[lastIndex] : empty;
    }

    public static String extensionFromName(String filename) {
        final String empty = "";
        if (!Utilities.notNullOrEmpty(filename)) {
            return empty;
        }

        return !filename.contains(".") ? empty :
                filename.substring(filename.lastIndexOf(".")).replace(".", "");
    }

    public static String mimeTypeFromPath(String path) {
        final String empty = "";
        if (!Utilities.notNullOrEmpty(path)) {
            return empty;
        }

        return MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(path);
    }
}
