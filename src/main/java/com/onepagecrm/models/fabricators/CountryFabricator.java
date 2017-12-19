package com.onepagecrm.models.fabricators;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.Countries;
import com.onepagecrm.models.internal.Country;
import com.onepagecrm.models.internal.FileUtilities;
import com.onepagecrm.models.serializers.CountrySerializer;
import com.onepagecrm.net.Response;

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
        String body = FileUtilities.getResourceContents(path);
        Response response = Response.okay(body);
        try {
            countries = CountrySerializer.fromResponse(response);
        } catch (APIException e) {
            LOG.severe("Problem creating countries list from JSON file.");
            LOG.severe(e.toString());
        }
        return countries;
    }
}
