package com.onepagecrm.models.serializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.onepagecrm.models.internal.OPCRMColors;

public class DateSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(DateSerializer.class.getName());

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
	    "yyyy-MM-dd'T'HH:mm:ss.SSS");
    public static SimpleDateFormat friendlyDateFormat = new SimpleDateFormat("MMM dd");

    private static final String ASAP = "ASAP";
    private static final String TODAY = "TODAY";
    private static final String WAITING = "WAITING";

    private static final int ASAP_OVERDUE_COLOR = OPCRMColors.FLAG_RED;
    private static final int TODAY_COLOR = OPCRMColors.FLAG_ORANGE;
    private static final int FUTURE_WAITING_COLOR = OPCRMColors.FLAG_GREY_BROWN;

    public static Date fromFormattedString(String dateStr) {
	if (dateStr != null) {
	    try {
		return dateTimeFormat.parse(dateStr);
	    } catch (ParseException e) {
		try {
		    return dateFormat.parse(dateStr);
		} catch (ParseException ex) {
		    LOG.severe("Error parsing date string to date object");
		    LOG.severe(e.toString());
		    LOG.severe(ex.toString());
		}
	    }
	}
	return null;
    }

    public static String toFriendlyDateString(Date date) {
	Date today = new Date();
	if (dateFormat.format(date).equals(dateFormat.format(today))) {
	    return TODAY;
	} else {
	    return friendlyDateFormat.format(date);
	}
    }

    public static int getDateColour(Date date, String status) {
	if (date != null) {
	    return getColorByDate(date);
	} else if (status != null) {
	    return getColorByStatus(status);
	} else {
	    return FUTURE_WAITING_COLOR;
	}
    }

    public static int getColorByDate(Date date) {
	Date today = new Date();
	Date todayDate = null, actionDate = null;
	try {
	    todayDate = dateFormat.parse(dateFormat.format(today));
	    actionDate = dateFormat.parse(dateFormat.format(date));
	} catch (ParseException e) {
	    LOG.severe("Error creating date object using specified format");
	    LOG.severe(e.toString());
	}
	if (actionDate.after(todayDate)) {
	    return FUTURE_WAITING_COLOR;
	} else if (actionDate.equals(todayDate)) {
	    return TODAY_COLOR;
	} else if (actionDate.before(todayDate)) {
	    return ASAP_OVERDUE_COLOR;
	} else {
	    return FUTURE_WAITING_COLOR;
	}
    }

    public static int getColorByStatus(String status) {
	if (status.equalsIgnoreCase(WAITING)) {
	    return FUTURE_WAITING_COLOR;
	} else if (status.equalsIgnoreCase(TODAY)) {
	    return TODAY_COLOR;
	} else if (status.equalsIgnoreCase(ASAP)) {
	    return ASAP_OVERDUE_COLOR;
	} else {
	    return FUTURE_WAITING_COLOR;
	}
    }
}
