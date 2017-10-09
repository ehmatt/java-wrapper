package com.onepagecrm.models.fabricators;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Countries;
import com.onepagecrm.models.internal.Country;
import com.onepagecrm.models.internal.FileUtilities;
import com.onepagecrm.models.serializers.CountrySerializer;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
@SuppressWarnings("WeakerAccess")
public class CountryFabricator extends BaseFabricator {

    private static final Logger LOG = Logger.getLogger(CountryFabricator.class.getName());

    public static Country single() {
        return ireland();
    }

    public static Country ireland() {
        return new Country()
                .setCode("IE")
                .setName("Ireland")
                .setPrefix("+353");
    }

    public static Countries list() {
        Countries countries = new Countries();
        String path = OnePageCRM.ASSET_PATH + "countries.json";
        String response = FileUtilities.getResourceContents(path);
        if (response != null) {
            try {
                countries = CountrySerializer.fromString(response);
            } catch (OnePageException e) {
                LOG.severe("Problem creating countries list from JSON file.");
                LOG.severe(e.toString());
            }
        }
        return countries;
    }
}
