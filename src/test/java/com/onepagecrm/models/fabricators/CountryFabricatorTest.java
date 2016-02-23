package com.onepagecrm.models.fabricators;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.Countries;
import com.onepagecrm.models.internal.Country;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
public class CountryFabricatorTest extends BaseTest {

    public void testSingle_correctValues() {
        Country country = CountryFabricator.single();
        assertEquals("Ireland", country.getName());
        assertEquals("IE", country.getCode());
        assertEquals("+353", country.getPrefix());
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public void testList_allCountriesValid() {
        Countries countries = CountryFabricator.list();
        assertEquals("Should be 250 contacts", countries.size(), 250);

        for (int i = 0; i < countries.size(); i++) {
            Country country = countries.get(i);
            assertNotNull(country.getName());
            assertNotNull(country.getCode());
            assertNotNull(country.getPrefix());
        }
    }
}
