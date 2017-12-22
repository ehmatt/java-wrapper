package com.onepagecrm.models.serializers;

import com.onepagecrm.models.CustomField;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 20/12/2017.
 */
public class CustomContactFieldSerializer extends CustomFieldBaseSerializer {

    private static final Logger LOG = Logger.getLogger(CustomContactFieldSerializer.class.getName());

    private static volatile CustomContactFieldSerializer instance;

    public static CustomContactFieldSerializer getInstance() {
        if (instance == null) {
            synchronized (CustomContactFieldSerializer.class) {
                if (instance == null) {
                    instance = new CustomContactFieldSerializer(CustomField.CF_TYPE_CONTACT);
                }
            }
        }
        return instance;
    }

    private CustomContactFieldSerializer(String type) {
        super(type);
    }
}
