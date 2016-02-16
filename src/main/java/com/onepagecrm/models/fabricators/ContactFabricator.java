package com.onepagecrm.models.fabricators;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.internal.Utilities;
import com.onepagecrm.models.serializers.ContactListSerializer;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class ContactFabricator extends BaseFabricator {

    private static final Logger LOG = Logger.getLogger(ContactFabricator.class.getName());

    public static Contact single() {
        // Create new Contact with desired fields.
        Contact contact = new Contact()
                .setId("5694e1999007ba687b0000c9")
                .setFirstName("Java")
                .setLastName("Wrapper")
                .setJobTitle("Software Developer")
                .setCompanyName("OnePageCRM")
                .setBackground("Java is very verbose, we met in college.")
                .setPhotoUrl("https://opcrm-fd-us-west-1-54e488299007ba1342000001.s3.amazonaws.com/" +
                        "559cd1866f6e656707000001/56b0c3919007ba1421000007/56b0c3919007ba1421000006.png")
                .setStarred(true)
                .setOwnerId("559cd1866f6e656707000001")
                .setLeadSourceId("email_web")
                .setStatus("Prospect")
                .setStatusId("559cd1a06f6e656707000006")
                .setHasPendingDeal(true)
                .setTotalPending(20400d);

        // Address address fields.
        contact.setAddress(AddressFabricator.single());

        // Phone, Email, Web.
        addContactables(contact);

        // Next Action.
        contact.setNextAction(ActionFabricator.single());

        return contact;
    }

    public static Contact importContact() {
        // Create new Contact with desired fields.
        Contact contact = new Contact()
                .setFirstName("Elon")
                .setLastName("Musk")
                .setJobTitle("Entrepreneur|Engineer|Inventor|Investor")
                .setCompanyName("SpaceX|Tesla Motors|PayPal|SolarCity")
                .setBackground("Elon Reeve Musk (born June 28, 1971) is a South African-born " +
                        "Canadian-American business magnate, engineer, inventor and investor. " +
                        "He is the CEO and CTO of SpaceX, CEO and product architect of Tesla " +
                        "Motors, chairman of SolarCity, and co-chairman of OpenAI.")
                .setPhotoUrl("https://onepagecrm-ud2-us-west-1.s3.amazonaws.com/5694e1999007b" +
                        "a687b0000c9/1454424977000/56b0c3919007ba1421000006.png")
                .setStarred(true);

        // Address address fields.
        contact.setAddress(AddressFabricator.importAddress());

        // Phone, Email, Web.
        addContactables(contact);

        return contact;
    }

    private static void addContactables(Contact contact) {
        // Add list of phones to our Contact.
        contact.setPhones(PhoneFabricator.list());
        // Add list of emails to our Contact.
        contact.setEmails(EmailFabricator.list());
        // Add list of urls to our Contact.
        contact.setUrls(UrlFabricator.list());
    }

    public static ContactList list() {
        String list = Utilities.getResourceContents("src/test/res/request_01_action_stream_response.json");
        ContactList contacts = new ContactList();
        try {
            contacts = ContactListSerializer.fromString(list);
        } catch (OnePageException e) {
            LOG.severe("Problem creating contact list from JSON file.");
            LOG.severe(e.toString());
        }
        return contacts;
    }
}
