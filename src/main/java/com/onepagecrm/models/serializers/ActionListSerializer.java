package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.models.Action;
import com.onepagecrm.net.Response;
import org.json.JSONObject;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 19/12/2017.
 */
public class ActionListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ActionListSerializer.class.getName());

    public static List<Action> fromResponse(Response response) throws APIException {
        JSONObject dataObject = (JSONObject) BaseSerializer.fromResponse(response);
        return ActionSerializer.fromJsonArray(dataObject.optJSONArray(ACTIONS_TAG));
    }
}
