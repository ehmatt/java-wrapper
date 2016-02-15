package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class FilterFabricator extends BaseFabricator {

    public static Filter single() {
        return new Filter()
                .setId("556cb8c91787fa0e24000042")
                .setName("Pending deals");
    }

    public static List<Filter> list() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter()
                .setId("556cb8c91787fa0e24000042")
                .setName("Pending deals")
        );
        filters.add(new Filter()
                .setId("556cb8c91787fa0e24000041")
                .setName("Prospects with pending deals")
        );
        return filters;
    }
}
