package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
@SuppressWarnings("unused")
public class PhoneFabricator extends BaseFabricator {

    public static Phone single() {
        return new Phone()
                .setType(Phone.TYPE_WORK)
                .setValue("0853334444");
    }

    public static List<Phone> list() {
        // Create a list for phone numbers.
        List<Phone> phones = new ArrayList<>();

        Phone work = new Phone();
        work.setType(Phone.TYPE_WORK);
        work.setValue("091123456");
        phones.add(work);

        Phone mobile = new Phone();
        mobile.setType(Phone.TYPE_MOBILE);
        mobile.setValue("0877734242");
        phones.add(mobile);

        Phone home = new Phone();
        home.setType(Phone.TYPE_HOME);
        home.setValue("091654321");
        phones.add(home);

        Phone direct = new Phone();
        direct.setType(Phone.TYPE_DIRECT);
        direct.setValue("0868825552");
        phones.add(direct);

        Phone fax = new Phone();
        fax.setType(Phone.TYPE_FAX);
        fax.setValue("091754409");
        phones.add(fax);

        Phone skype = new Phone();
        skype.setType(Phone.TYPE_SKYPE);
        skype.setValue("+35391998877");
        phones.add(skype);

        Phone other = new Phone();
        other.setType(Phone.TYPE_OTHER);
        other.setValue("+353868825552");
        phones.add(other);

        return phones;
    }

    public static List<Phone> singleItemList() {
        // Create a list for phone numbers.
        List<Phone> phones = new ArrayList<>();
        // Add one item.
        phones.add(single());
        return phones;
    }
}
