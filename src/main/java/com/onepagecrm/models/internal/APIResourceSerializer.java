package com.onepagecrm.models.internal;

import com.onepagecrm.models.serializers.BaseSerializer;
import com.onepagecrm.net.ApiResource;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 04/09/2017.
 */
public class APIResourceSerializer<T extends ApiResource> extends BaseSerializer {

    static volatile APIResourceSerializer instance;

    public static APIResourceSerializer getsInstance() {
        if (instance == null) {
            synchronized (APIResourceSerializer.class) {
                if (instance == null) {
                    instance = new APIResourceSerializer();
                }
            }
        }
        return instance;
    }
}
