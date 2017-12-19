package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.Address;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
public class AddressFabricator extends BaseFabricator {

    public static Address single() {
        return onePageHQ();
    }

    public static Address onePageHQ() {
        return new Address()
                .setAddress("Unit 5, Business Innovation Center, NUIG")
                .setCity("Galway")
                .setState("Connacht")
                .setZipCode("HJ12WE3")
                .setCountryCode("IE");
    }

    public static Address importAddress() {
        return new Address()
                .setAddress("Bel Air")
                .setCity("Los Angeles")
                .setState("California")
                .setZipCode("90077")
                .setCountryCode("US");
    }
}
