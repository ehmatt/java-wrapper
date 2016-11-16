package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.Device;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.onepagecrm.models.serializers.DeviceSerializer.fromJsonArray;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 26/10/2016.
 */
public class DeviceListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(DeviceListSerializer.class.getSimpleName());

    public static List<Device> fromString(String responseBody) throws OnePageException {
        String parsedResponse;
        OnePageException exception;
        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject responseObject = new JSONObject(parsedResponse);
            return fromJsonArray(responseObject.optJSONArray(FCM_DEVICES_TAG));

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;
        } catch (JSONException e) {
            LOG.severe("Error parsing JSON" + e);
            return new ArrayList<>();
        }
    }
}
