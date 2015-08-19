package com.onepagecrm.models.serializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class DateSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(DateSerializer.class.getName());

    public static Date fromFormattedString(String dateStr) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	if (dateStr != null) {
	    try {
		return formatter.parse(dateStr);
	    } catch (ParseException e) {
		try {
		    formatter = new SimpleDateFormat("yyyy-MM-dd");
		    return formatter.parse(dateStr);
		} catch (ParseException ex) {
		    LOG.severe("Error parsing date string to date object");
		    LOG.severe(e.toString());
		    LOG.severe(ex.toString());
		}
	    }
	}
	return null;
    }
}
