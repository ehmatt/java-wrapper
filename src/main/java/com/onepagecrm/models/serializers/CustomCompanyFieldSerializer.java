package com.onepagecrm.models.serializers;

import com.onepagecrm.models.CustomField;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 20/12/2017.
 */
public class CustomCompanyFieldSerializer extends CustomFieldBaseSerializer {

    private static final Logger LOG = Logger.getLogger(CustomCompanyFieldSerializer.class.getName());

    private static volatile CustomCompanyFieldSerializer instance;

    public static CustomCompanyFieldSerializer getInstance() {
        if (instance == null) {
            synchronized (CustomCompanyFieldSerializer.class) {
                if (instance == null) {
                    instance = new CustomCompanyFieldSerializer(CustomField.CF_TYPE_COMPANY);
                }
            }
        }
        return instance;
    }

    private CustomCompanyFieldSerializer(String type) {
        super(type);
    }
}
