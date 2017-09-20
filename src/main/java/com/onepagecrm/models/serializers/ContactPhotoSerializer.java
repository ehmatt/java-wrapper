package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.Contact;
import com.onepagecrm.net.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 18/09/2016.
 */
public class ContactPhotoSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactPhotoSerializer.class.getName());

    public static Contact fromResponse(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        return ContactSerializer.fromJsonObject(dataObject);
    }

    // TODO: delete
    public static Contact fromString(String responseBody) throws APIException {
        try {
            String dataString = (String) BaseSerializer.fromString(responseBody);
            JSONObject parsedObject = new JSONObject(dataString);
            return ContactSerializer.fromJsonObject(parsedObject);

        } catch (JSONException e) {
            LOG.severe("Could not create JSON object from response");
            LOG.severe(e.toString());
        }
        return new Contact();
    }


    public static JSONObject toJsonObject(String base64EncodedImageString) {
        JSONObject imageObject = new JSONObject();
        addJsonStringValue(base64EncodedImageString, imageObject, IMAGE_TAG);
        return imageObject;
    }

    public static String toJsonString(String base64EncodedImageString) {
        return toJsonObject(base64EncodedImageString).toString();
    }
}
