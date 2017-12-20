package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Address;
import com.onepagecrm.models.serializers.impl.SerializableResource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.logging.Logger;

import static com.onepagecrm.models.internal.Utilities.nullChecks;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 18/09/2016.
 */
public class AddressSerializer extends SerializableResource<Address> {

    private static final Logger LOG = Logger.getLogger(AddressSerializer.class.getName());

    private static volatile AddressSerializer instance;

    public static AddressSerializer getInstance() {
        if (instance == null) {
            synchronized (AddressSerializer.class) {
                if (instance == null) {
                    instance = new AddressSerializer();
                }
            }
        }
        return instance;
    }

    @Override
    protected Address singleResource() {
        return new Address();
    }

    @Override
    protected String singleTag() {
        return BaseSerializer.ADDRESS_TAG;
    }

    @Override
    protected String multipleTag() {
        return BaseSerializer.ADDRESS_LIST_TAG;
    }

    @Override
    protected Address fromJsonObjectImpl(JSONObject addressObject) {
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
            return singleResource();
        }
    }

    @Override
    protected JSONObject toJsonObjectImpl(Address address) {
        JSONObject addressObject = new JSONObject();
        addJsonStringValue(address.getAddress(), addressObject, ADDRESS_TAG);
        addJsonStringValue(address.getCity(), addressObject, CITY_TAG);
        addJsonStringValue(address.getState(), addressObject, STATE_TAG);
        addJsonStringValue(address.getZipCode(), addressObject, ZIP_CODE_TAG);
        addJsonStringValue(address.getCountryCode(), addressObject, COUNTRY_CODE_TAG);
        return addressObject;
    }

    public JSONArray singleToJsonArray(Address address) {
        JSONArray addressArray = new JSONArray();
        if (address == null) return addressArray;
        List<Address> list = multipleResources();
        list.add(address);
        return toJsonArray(list);
    }

    public Address singleFromJsonArray(JSONArray addressArray) {
        List<Address> addresses = fromJsonArray(addressArray);
        return !addresses.isEmpty() ? addresses.get(0) : singleResource();
    }
}
