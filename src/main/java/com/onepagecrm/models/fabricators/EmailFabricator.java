package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
public class EmailFabricator extends BaseFabricator {

    public static Email single() {
        return new Email()
                .setType(Email.TYPE_WORK)
                .setValue("0853334444");
    }

    public static List<Email> list() {
        // Create a list for Email addresses.
        List<Email> emails = new ArrayList<>();

        Email work = new Email();
        work.setType(Email.TYPE_WORK);
        work.setValue("work@domain.com");
        emails.add(work);

        Email home = new Email();
        home.setType(Email.TYPE_HOME);
        home.setValue("home@domain.com");
        emails.add(home);

        Email other = new Email();
        other.setType(Email.TYPE_OTHER);
        other.setValue("other@domain.com");
        emails.add(other);

        return emails;
    }

    public static List<Email> singleItemList() {
        // Create a list for Email addresses.
        List<Email> emails = new ArrayList<>();
        // Add one item.
        emails.add(single());
        return emails;
    }
}
