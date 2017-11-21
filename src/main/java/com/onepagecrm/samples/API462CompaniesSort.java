package com.onepagecrm.samples;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Company;
import com.onepagecrm.models.CompanyList;
import com.onepagecrm.models.User;
import com.onepagecrm.net.request.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

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
        //params.put("sort_by", "name"); // TODO: API does not support sort by name??
        CompanyList companies = Company.list(params);

        List<String> namesFromAPI = new LinkedList<>();
        List<String> namesFromSort = new LinkedList<>();

        for (Company company : companies) {
            namesFromAPI.add(company.getName());
            namesFromSort.add(company.getName());
        }

        Collections.sort(namesFromSort, new Comparator<String>() {
            @Override
            public int compare(String name1, String name2) {
                return name1.compareToIgnoreCase(name2);
            }
        });

        int i = 0;
        for (String sortedName : namesFromSort) {
            String apiName = namesFromAPI.get(i++);
            LOG.info("API: \"" + apiName + "\" - SORT: \"" + sortedName
                    + "\" - match: " + sortedName.equalsIgnoreCase(apiName));
        }
    }
}
