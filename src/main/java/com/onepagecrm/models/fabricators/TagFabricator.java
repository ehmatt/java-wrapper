package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
@SuppressWarnings("unused")
public class TagFabricator extends BaseFabricator {

    public static Tag single() {
        return new Tag()
                .setName("VIP");
    }

    public static List<Tag> list() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag().setName("AndroidTagTest"));
        tags.add(new Tag().setName("iOSTagTest"));
        tags.add(new Tag().setName("VIP"));
        return tags;
    }
}
