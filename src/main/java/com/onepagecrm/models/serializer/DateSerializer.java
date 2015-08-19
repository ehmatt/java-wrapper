package com.onepagecrm.models.serializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class DateSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(DateSerializer.class.getName());

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
	    "yyyy-MM-dd'T'HH:mm:ss.SSS");
    public static SimpleDateFormat friendlyDateFormat = new SimpleDateFormat("MMM dd");
    
    private static final String TODAY = "TODAY";

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
}
