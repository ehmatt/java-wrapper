package com.onepagecrm.models.internal;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 29/06/2017.
 */
@SuppressWarnings("WeakerAccess")
public class DealCalculationHelper {

    public static double margin(double amount, double cost) {
        return amount - cost;
    }

    public static double percentage(double absolute, double margin) {
        return (absolute / margin) * 100;
    }

    public static double absolute(double percentage, double margin) {
        return (percentage * margin) / 100;
    }
}
