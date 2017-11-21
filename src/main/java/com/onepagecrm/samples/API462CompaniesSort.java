package com.onepagecrm.samples;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Company;
import com.onepagecrm.models.CompanyList;
import com.onepagecrm.models.User;
import com.onepagecrm.models.serializers.DateSerializer;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;
import static com.onepagecrm.models.internal.Utilities.repeatedString;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 21/11/2017.
 */
public class API462CompaniesSort {

    private static final Logger LOG = Logger.getLogger(Driver.class.getName());

    public static void main(String[] args) throws OnePageException {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // Load the properties file
            prop.load(input);

        } catch (IOException e) {
            LOG.severe("Error loading the config.properties file");
            LOG.severe(e.toString());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.severe("Error closing the config.properties file");
                    LOG.severe(e.toString());
                }
            }
        }

        OnePageCRM.setServer(Request.DEV_SERVER);

        User loggedInUser = User.login(
                prop.getProperty("username"),
                prop.getProperty("password"));

        LOG.info("Logged in User : " + loggedInUser);

        Map<String, Object> params = new HashMap<>();
        params.put("order", "asc");

        params.put("sort_by", "company_name"); // TODO: sort by "name"??
        CompanyList azAPI = Company.list(params);
        CompanyList azManual = (CompanyList) azAPI.clone();

        params.put("sort_by", "created_at");
        CompanyList createdAtAPI = Company.list(params);
        CompanyList createdAtManual = (CompanyList) createdAtAPI.clone();

        params.put("sort_by", "modified_at");
        CompanyList modifiedAtAPI = Company.list(params);
        CompanyList modifiedAtManual = (CompanyList) modifiedAtAPI.clone();

        // AZ sorting.
        Collections.sort(azManual, new Comparator<Company>() {
            @Override
            public int compare(Company c1, Company c2) {
                final String cmp1 = notNullOrEmpty(c1.getName()) ? c1.getName() : "";
                final String cmp2 = notNullOrEmpty(c2.getName()) ? c2.getName() : "";
                return cmp1.compareToIgnoreCase(cmp2);
            }
        });

        // AZ checking.
        LOG.info(repeatedString("*", 5) + " AZ " + repeatedString("*", 5));
        int i = 0;
        for (Company sorted : azManual) {
            String sortedName = sorted.getName();
            String apiName = azAPI.get(i++).getName();
            LOG.info("API: \"" + apiName + "\" - SORT: \"" + sortedName
                    + "\" - match: " + sortedName.equalsIgnoreCase(apiName));
        }

        // created_at sorting.
        Collections.sort(createdAtManual, new Comparator<Company>() {
            @Override
            public int compare(Company c1, Company c2) {
                final long cmp1 = c1.getCreatedAt() != null ? DateSerializer.toTimestamp(c1.getCreatedAt()) : 0L;
                final long cmp2 = c2.getCreatedAt() != null ? DateSerializer.toTimestamp(c2.getCreatedAt()) : 0L;
                return Long.compare(cmp1, cmp2);
            }
        });

        // created_at checking.
        LOG.info(repeatedString("*", 5) + " created_at " + repeatedString("*", 5));
        i = 0;
        for (Company sorted : createdAtManual) {
            Date sortedTime = sorted.getCreatedAt();
            Date apiTime = createdAtAPI.get(i++).getCreatedAt();
            LOG.info("API: \"" + sortedTime + "\" - SORT: \"" + apiTime
                    + "\" - match: " + sortedTime.equals(apiTime));
        }

        // modified_at sorting.
        Collections.sort(modifiedAtManual, new Comparator<Company>() {
            @Override
            public int compare(Company c1, Company c2) {
                final long cmp1 = c1.getModifiedAt() != null ? DateSerializer.toTimestamp(c1.getModifiedAt()) : 0L;
                final long cmp2 = c2.getModifiedAt() != null ? DateSerializer.toTimestamp(c2.getModifiedAt()) : 0L;
                return Long.compare(cmp1, cmp2);
            }
        });

        // modified_at checking.
        LOG.info(repeatedString("*", 5) + " modified_at " + repeatedString("*", 5));
        i = 0;
        for (Company sorted : modifiedAtManual) {
            Date sortedTime = sorted.getModifiedAt();
            Date apiTime = modifiedAtAPI.get(i++).getModifiedAt();
            LOG.info("API: \"" + sortedTime + "\" - SORT: \"" + apiTime
                    + "\" - match: " + ((sortedTime == null && apiTime == null) || sortedTime != null && sortedTime.equals(apiTime)));
        }
    }
}
