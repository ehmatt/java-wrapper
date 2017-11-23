package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.DeleteResult;
import com.onepagecrm.net.Response;

/**
 * @author Cillian Myles (cillian@onepagecrm.com) on 11/24/15.
 */
public class DeleteResultSerializer extends BaseSerializer {

    public static DeleteResult fromResponse(String resourceId, Response response) throws APIException {
        BaseSerializer.fromResponse(response);
        return new DeleteResult(resourceId, true);
    }

    // TODO: throw APIException instead of OnePageException
    // TODO: eventually delete
    public static DeleteResult fromString(String resourceId, String responseBody) throws OnePageException {
        try {
            BaseSerializer.fromString(responseBody);
            return new DeleteResult(resourceId, true);

        } catch (ClassCastException e) {
            throw (OnePageException) BaseSerializer.fromString(responseBody);
        }
    }
}
