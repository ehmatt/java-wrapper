package com.onepagecrm.models;

import com.onepagecrm.BaseTest;
import com.onepagecrm.models.internal.Utilities;

public class UtilitiesTest extends BaseTest {

    public void testRepeatedString() throws Exception {
        String toBeRepeated = "hi";
        int times = 3;

        assertEquals("hihihi", Utilities.repeatedString(toBeRepeated, times));
    }

    public void testRepeatedChar() throws Exception {
        char toBeRepeated = 'x';
        int times = 5;

        assertEquals("xxxxx", Utilities.repeatedChar(toBeRepeated, times));
    }
}
