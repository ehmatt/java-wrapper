package com.onepagecrm;

import junit.framework.TestCase;

import java.util.logging.Logger;

public class BaseTest extends TestCase {

    private static Logger LOG = Logger.getLogger(BaseTest.class.getName());

    /**
     * Must be one testcase here since we're extending it.
     */
    public void testApplication() {
        assertNull(OnePageCRM.account);
    }
}
