package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Address;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.onepagecrm.models.internal.Utilities.nullChecks;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 18/09/2016.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AddressSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(AddressSerializer.class.getName());

    private static Address DEFAULT = new Address();

    public static Address fromJsonObject(JSONObject addressObject) {
        if (addressObject == null) {
            return DEFAULT;
        }
        Address address = new Address();
        try {
            if (addressObject.has(ADDRESS_TAG)) {
                address.setAddress(nullChecks(addressObject.optString(ADDRESS_TAG)));
            }
            if (addressObject.has(CITY_TAG)) {
                address.setCity(nullChecks(addressObject.optString(CITY_TAG)));
            }
            if (addressObject.has(STATE_TAG)) {
                address.setState(nullChecks(addressObject.optString(STATE_TAG)));
            }
            if (addressObject.has(ZIP_CODE_TAG)) {
                address.setZipCode(nullChecks(addressObject.optString(ZIP_CODE_TAG)));
            }
            if (addressObject.has(COUNTRY_CODE_TAG)) {
                address.setCountryCode(nullChecks(addressObject.optString(COUNTRY_CODE_TAG)));
            }
            return address;

        } catch (Exception e) {
            LOG.severe("Error parsing Address object");
            LOG.severe(e.toString());
            return DEFAULT;
        }
    }

    public static List<Address> fromJsonArray(JSONArray addressArray) {
        List<Address> addresses = new ArrayList<>();
        if (addressArray == null) return addresses;
        JSONObject addressObject;
        for (int i = 0; i < addressArray.length(); i++) {
            addressObject = addressArray.optJSONObject(i);
            addresses.add(fromJsonObject(addressObject));
        }
        return addresses;
    }

    public static JSONObject toJsonObject(Address address) {
        JSONObject addressObject = new JSONObject();
        if (address == null) return addressObject;
        addJsonStringValue(address.getAddress(), addressObject, ADDRESS_TAG);
        addJsonStringValue(address.getCity(), addressObject, CITY_TAG);
        addJsonStringValue(address.getState(), addressObject, STATE_TAG);
        addJsonStringValue(address.getZipCode(), addressObject, ZIP_CODE_TAG);
        addJsonStringValue(address.getCountryCode(), addressObject, COUNTRY_CODE_TAG);
        return addressObject;
    }

    public static JSONArray toJsonArray(List<Address> addresses) {
        JSONArray addressArray = new JSONArray();
        if (addresses == null) return addressArray;
        for (Address address : addresses) {
            addressArray.put(toJsonObject(address));
        }
        return addressArray;
    }

    public static JSONArray singleToJsonArray(Address address) {
        JSONArray addressArray = new JSONArray();
        if (address == null) return addressArray;
        List<Address> list = new ArrayList<>();
        list.add(address);
        return toJsonArray(list);
    }

    public static Address singleFromJsonArray(JSONArray addressArray) {
        List<Address> addresses = fromJsonArray(addressArray);
        return !addresses.isEmpty() ? addresses.get(0) : DEFAULT;
    }

    public static String toJsonString(Address address) {
        return toJsonObject(address).toString();
    }

    public static String toJsonString(List<Address> addresses) {
        return toJsonArray(addresses).toString();
    }
}
