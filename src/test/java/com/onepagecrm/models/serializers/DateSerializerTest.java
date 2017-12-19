package com.onepagecrm.models.serializers;

import com.onepagecrm.BaseTest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 20/07/2016.
 */
@SuppressWarnings("RedundantThrows")
public class DateSerializerTest extends BaseTest {

    // Thu, 01 Jan 1970 00:00:00 UTC = 0
    private final long theBeginningTs = 0;
    private Date theBeginning;

    // Fri, 01 Jul 2016 08:00:00 UTC = 1467360000
    private final long julyFirst2016Morning8Ts = 1467360000;
    private Date julyFirst2016Morning8;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Calendar theBeginningCal = new GregorianCalendar();
        theBeginningCal.setTimeZone(TimeZone.getTimeZone("UTC"));
        theBeginningCal.set(Calendar.YEAR, 1970);
        theBeginningCal.set(Calendar.MONTH, Calendar.JANUARY);
        theBeginningCal.set(Calendar.DAY_OF_MONTH, 1);
        theBeginningCal.set(Calendar.AM_PM, Calendar.AM);
        theBeginningCal.set(Calendar.HOUR, 0);
        theBeginningCal.set(Calendar.MINUTE, 0);
        theBeginningCal.set(Calendar.SECOND, 0);
        theBeginningCal.set(Calendar.MILLISECOND, 0);
        theBeginning = theBeginningCal.getTime();

        Calendar theOtherCal = new GregorianCalendar();
        theOtherCal.setTimeZone(TimeZone.getTimeZone("UTC"));
        theOtherCal.set(Calendar.YEAR, 2016);
        theOtherCal.set(Calendar.MONTH, Calendar.JULY);
        theOtherCal.set(Calendar.DAY_OF_MONTH, 1);
        theOtherCal.set(Calendar.AM_PM, Calendar.AM);
        theOtherCal.set(Calendar.HOUR, 8);
        theOtherCal.set(Calendar.MINUTE, 0);
        theOtherCal.set(Calendar.SECOND, 0);
        theOtherCal.set(Calendar.MILLISECOND, 0);
        julyFirst2016Morning8 = theOtherCal.getTime();
    }

    public void testTimestampParsing() throws Exception {
        assertEquals(theBeginning, DateSerializer.fromTimestamp(String.valueOf(theBeginningTs)));
        assertEquals(julyFirst2016Morning8, DateSerializer.fromTimestamp(String.valueOf(julyFirst2016Morning8Ts)));
    }

    public void testTimestampCreation() throws Exception {
        assertEquals(theBeginningTs, (long) DateSerializer.toTimestamp(theBeginning));
        assertEquals(julyFirst2016Morning8Ts, (long) DateSerializer.toTimestamp(julyFirst2016Morning8));
    }
}
