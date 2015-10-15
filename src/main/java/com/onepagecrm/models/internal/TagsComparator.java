package com.onepagecrm.models.internal;

import com.onepagecrm.models.Tag;

public class TagsComparator implements java.util.Comparator<Tag> {

    /**
     * Method which will allow Tags to be alphabetically sorted.
     *
     * @param tag1
     * @param tag2
     * @return
     */
    @Override
    public int compare(final Tag tag1, final Tag tag2) {
        String name1 = tag1.getName().toLowerCase();
        String name2 = tag2.getName().toLowerCase();
        return name1.compareTo(name2);
    }
}
