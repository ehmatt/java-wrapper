package com.onepagecrm.models.serializers;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.DeleteResult;

/**
 * Created by Cillian Myles (cillian@onepagecrm.com) on 11/24/15.
 */
public class DeleteResultSerializer extends BaseSerializer {

    public static DeleteResult fromString(String pResourceId, String responseBody) throws OnePageException {
        try {
            BaseSerializer.fromString(responseBody);
            return new DeleteResult(pResourceId, true);

        } catch (ClassCastException e) {
            throw (OnePageException) BaseSerializer.fromString(responseBody);
        }
    }
}
