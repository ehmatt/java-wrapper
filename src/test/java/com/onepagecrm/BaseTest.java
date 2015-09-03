package com.onepagecrm;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class BaseTest extends TestCase {

    private static Logger LOG = Logger.getLogger(BaseTest.class.getName());

    /**
     * Must be one testcase here since we're extending it.
     */
    public void testApplication() {
        assertNull(OnePageCRM.account);
    }

    /**
     * Get the String file contents at a given path.
     *
     * @param path
     * @return
     */
    protected String resource(String path) {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
