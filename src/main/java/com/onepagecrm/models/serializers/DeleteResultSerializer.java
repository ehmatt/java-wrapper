package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.APIException;
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
}
