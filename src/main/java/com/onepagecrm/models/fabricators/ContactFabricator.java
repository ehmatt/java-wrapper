package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class ContactFabricator extends BaseFabricator {

    public static Contact fromAddressBook() {
        // Create new Contact with desired fields.
        Contact lContact = new Contact()
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
        lContact.setAddress(fullAddress());

        // Add list of phones to our Contact.
        lContact.setPhones(fullListOfPhones());

        // Add list of emails to our Contact.
        lContact.setEmails(fullListOfEmails());

        // Add list of urls to our Contact.
        lContact.setUrls(fullListOfUrls());

        return lContact;
    }

    public static Address fullAddress() {
        return new Address()
                .setAddress("Bel Air")
                .setCity("Los Angeles")
                .setState("California")
                .setZipCode("90077")
                .setCountryCode("US");
    }

    public static List<Phone> fullListOfPhones() {
        // Create a list for phone numbers.
        List<Phone> phones = new ArrayList<>();

        Phone work = new Phone();
        work.setType("Work");
        work.setValue("091123456");
        phones.add(work);

        Phone mobile = new Phone();
        mobile.setType("Mobile");
        mobile.setValue("0877734242");
        phones.add(mobile);

        Phone home = new Phone();
        home.setType("Home");
        home.setValue("091654321");
        phones.add(home);

        Phone direct = new Phone();
        direct.setType("Direct");
        direct.setValue("0868825552");
        phones.add(direct);

        Phone fax = new Phone();
        fax.setType("Fax");
        fax.setValue("091754409");
        phones.add(fax);

        Phone skype = new Phone();
        skype.setType("Skype");
        skype.setValue("+35391998877");
        phones.add(skype);

        Phone other = new Phone();
        other.setType("Other");
        other.setValue("+353868825552");
        phones.add(other);

        return phones;
    }

    public static List<Phone> onePhoneList() {
        // Create a list for phone numbers.
        List<Phone> phones = new ArrayList<>();

        Phone work = new Phone();
        work.setType("Work");
        work.setValue("0853334444");
        phones.add(work);

        return phones;
    }

    public static List<Email> fullListOfEmails() {
        // Create a list for Email addresses.
        List<Email> emails = new ArrayList<>();

        Email workEmail = new Email();
        workEmail.setType("Work");
        workEmail.setValue("work@domain.com");
        emails.add(workEmail);

        Email homeEmail = new Email();
        homeEmail.setType("Home");
        homeEmail.setValue("home@domain.com");
        emails.add(homeEmail);

        Email otherEmail = new Email();
        otherEmail.setType("Other");
        otherEmail.setValue("other@domain.com");
        emails.add(otherEmail);

        return emails;
    }

    public static List<Url> fullListOfUrls() {
        // Create a list for Web addresses.
        List<Url> urls = new ArrayList<>();

        Url website = new Url();
        website.setType("Website");
        website.setValue("www.website.com");
        urls.add(website);

        Url blog = new Url();
        blog.setType("Blog");
        blog.setValue("www.blog.com");
        urls.add(blog);

        Url twitter = new Url();
        twitter.setType("Twitter");
        twitter.setValue("www.twitter.com");
        urls.add(twitter);

        Url linkedIn = new Url();
        linkedIn.setType("LinkedIn");
        linkedIn.setValue("www.linkedin.com");
        urls.add(linkedIn);

        Url facebook = new Url();
        facebook.setType("Facebook");
        facebook.setValue("www.facebook.com");
        urls.add(facebook);

        Url googlePlus = new Url();
        googlePlus.setType("Google+");
        googlePlus.setValue("www.googleplus.com");
        urls.add(googlePlus);

        Url otherUrl = new Url();
        otherUrl.setType("Other");
        otherUrl.setValue("www.other.com");
        urls.add(otherUrl);

        return urls;
    }
}
