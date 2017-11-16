package com.onepagecrm.models.internal;

import com.onepagecrm.OnePageCRM;

import javax.activation.MimetypesFileTypeMap;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 28/09/2017.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class FileRefUtils {

    private static final Logger LOG = Logger.getLogger(FileRefUtils.class.getSimpleName());

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

        return OnePageCRM.MOBILE ? empty :
                MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(path);
    }
}
