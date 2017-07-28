package com.onepagecrm.models.helpers;

import com.onepagecrm.models.Tag;
import com.onepagecrm.models.serializers.BaseSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 27/07/2016.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class TagHelper {

    public static List<Tag> copy(List<Tag> tags) {
        List<Tag> copy = new ArrayList<>();
        if (tags == null) return copy;
        for (Tag tag : tags) {
            copy.add(new Tag(tag));
        }
        return copy;
    }

    public static List<String> asStrings(List<Tag> tags) {
        List<String> names = new ArrayList<>();
        if (tags == null) return names;
        for (Tag tag : tags) {
            names.add(new Tag(tag).getName());
        }
        return names;
    }

    public static List<Tag> asTags(List<String> names) {
        List<Tag> tags = new ArrayList<>();
        if (names == null) return tags;
        for (String name : names) {
            tags.add(new Tag().setName(name));
        }
        return tags;
    }

    public static String asString(List<Tag> tags) {
        return BaseSerializer.toCommaSeparatedString(asStrings(tags));
    }

    public static List<Tag> asTags(String string) {
        return TagHelper.asTags(BaseSerializer.toListOfStrings(string));
    }
}
