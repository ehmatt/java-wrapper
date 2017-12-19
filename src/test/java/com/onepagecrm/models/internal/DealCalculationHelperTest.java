package com.onepagecrm.models.internal;

import com.onepagecrm.BaseTest;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 29/06/2017.
 */
@SuppressWarnings("RedundantThrows")
public class DealCalculationHelperTest extends BaseTest {

    public void testMargin() throws Exception {
        double cost = 3d, amount = 4d, margin = 1d;
        assertEquals("Margin incorrect",
                margin, DealCalculationHelper.margin(amount, cost));
    }

    public void testPercentage() throws Exception {
        double absolute = 0.5d, margin = 2d, percentage = 25d;
        assertEquals("Percentage incorrect",
                percentage, DealCalculationHelper.percentage(absolute, margin));
    }

    public void testAbsolute() throws Exception {
        double percentage = 25d, margin = 2d, absolute = 0.5d;
        assertEquals("Absolute incorrect",
                absolute, DealCalculationHelper.absolute(percentage, margin));
    }
}
