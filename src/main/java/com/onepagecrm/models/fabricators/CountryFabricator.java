package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.internal.Country;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class CountryFabricator extends BaseFabricator {

    public static Country single() {
        return new Country()
                .setCode("IE")
                .setCurrency("euro")
                .setName("Ireland")
                .setPrefix("+353");
    }
}
