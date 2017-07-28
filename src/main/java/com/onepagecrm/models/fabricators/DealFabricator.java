package com.onepagecrm.models.fabricators;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Deal;
import com.onepagecrm.models.DealList;
import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.models.serializers.DealListSerializer;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
@SuppressWarnings("WeakerAccess")
public class DealFabricator extends BaseFabricator {

    private static final Logger LOG = Logger.getLogger(DealFabricator.class.getName());

    public static Deal single() {
        return regular();
    }

    public static Deal regular() {
        return list().get(1);
    }

    public static Deal multiMonth() {
        return list().get(0);
    }

    public static Deal won() {
        return list().get(12);
    }

    public static Deal lost() {
        return list().get(10);
    }

    public static Deal withCustomFields() {
        return list().get(14);
    }

    public static Deal commissionPercentage() {
        return list().get(15);
    }

    public static Deal commissionAbsolute() {
        return list().get(16);
    }

    public static DealList list() {
        // Create a list of Deals.
        DealList deals = new DealList();
        String path = OnePageCRM.ASSET_PATH + "deals.json";
        String response = Utilities.getResourceContents(path);
        if (response != null) {
            try {
                deals = DealListSerializer.fromString(response);
            } catch (OnePageException e) {
                LOG.severe("Problem creating user object from JSON file.");
                LOG.severe(e.toString());
            }
        }
        return deals;
    }
}
