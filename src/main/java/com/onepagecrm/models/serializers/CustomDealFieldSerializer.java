package com.onepagecrm.models.serializers;

import com.onepagecrm.models.CustomField;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 20/12/2017.
 */
public class CustomDealFieldSerializer extends CustomFieldBaseSerializer {

    private static final Logger LOG = Logger.getLogger(CustomDealFieldSerializer.class.getName());

    private static volatile CustomDealFieldSerializer instance;

    public static CustomDealFieldSerializer getInstance() {
        if (instance == null) {
            synchronized (CustomDealFieldSerializer.class) {
                if (instance == null) {
                    instance = new CustomDealFieldSerializer(CustomField.CF_TYPE_DEAL);
                }
            }
        }
        return instance;
    }

    private CustomDealFieldSerializer(String type) {
        super(type);
    }
}
