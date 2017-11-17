package com.onepagecrm.models.fabricators;

import com.onepagecrm.OnePageCRM;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Contact;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.internal.FileUtilities;
import com.onepagecrm.models.serializers.ContactListSerializer;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ContactFabricator extends BaseFabricator {

    private static final Logger LOG = Logger.getLogger(ContactFabricator.class.getName());

    public static Contact single() {
        return actionStream().get(0);
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
        return actionStream();
    }

    public static ContactList actionStream() {
        return fromPath("action_stream_page_1.json");
    }

    public static ContactList actionStreamPage2() {
        return fromPath("action_stream_page_2.json");
    }

    public static ContactList actionStreamPage3() {
        return fromPath("action_stream_page_3.json");
    }

    public static ContactList actionStreamPartial() {
        return actionStream().subList(0, 3);
    }

    public static ContactList actionStreamFilterTag() {
        return fromPath("action_stream_filter_tag.json");
    }

    public static ContactList contacts() {
        return fromPath("contacts_page_1.json");
    }

    public static ContactList contactsPage2() {
        return fromPath("contacts_page_2.json");
    }

    public static ContactList contactsPage3() {
        return fromPath("contacts_page_3.json");
    }

    public static ContactList contactsPartial() {
        return contacts().subList(0, 3);
    }

    public static ContactList contactsFilterTag() {
        return fromPath("contacts_filter_tag.json");
    }

    private static ContactList fromPath(String fileName) {
        ContactList contacts = new ContactList();
        String path = OnePageCRM.ASSET_PATH + fileName;
        String response = FileUtilities.getResourceContents(path);
        if (response != null) {
            try {
                contacts = ContactListSerializer.fromString(response);
            } catch (OnePageException e) {
                LOG.severe("Problem creating contact list from JSON file.");
                LOG.severe(e.toString());
            }
        }
        return contacts;
    }
}
