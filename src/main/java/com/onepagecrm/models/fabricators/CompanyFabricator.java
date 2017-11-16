package com.onepagecrm.models.fabricators;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Company;
import com.onepagecrm.models.CompanyList;
import com.onepagecrm.models.internal.FileUtilities;
import com.onepagecrm.models.serializers.CompanyListSerializer;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 19/12/2016.
 */
@SuppressWarnings({"SameParameterValue", "unused"})
public class CompanyFabricator extends BaseFabricator {

    private static final Logger LOG = Logger.getLogger(CompanyFabricator.class.getName());

    public static Company single() {
        return list().get(0);
    }

    public static CompanyList list() {
        return fromPath("companies.json");
    }

    private static CompanyList fromPath(String fileName) {
        CompanyList companies = new CompanyList();
        String path = OnePageCRM.ASSET_PATH + fileName;
        String response = FileUtilities.getResourceContents(path);
        if (response != null) {
            try {
                companies = CompanyListSerializer.fromString(response);
            } catch (OnePageException e) {
                LOG.severe("Problem creating company list from JSON file.");
                LOG.severe(e.toString());
            }
        }
        return companies;
    }
}
