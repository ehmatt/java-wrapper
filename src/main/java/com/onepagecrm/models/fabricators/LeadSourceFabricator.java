package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.LeadSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class LeadSourceFabricator extends BaseFabricator {

    public static LeadSource single() {
        return new LeadSource()
                .setId("advertisement")
                .setText("Advertisement");
    }

    public static List<LeadSource> list() {
        List<LeadSource> tags = new ArrayList<>();
        tags.add(new LeadSource()
                .setId("advertisement")
                .setText("Advertisement")
        );
        tags.add(new LeadSource()
                .setId("email_web")
                .setText("Email or Web")
        );
        tags.add(new LeadSource()
                .setId("list_generation")
                .setText("List Generation")
        );
        tags.add(new LeadSource()
                .setId("other")
                .setText("Other")
        );
        tags.add(new LeadSource()
                .setId("referral")
                .setText("Referral")
        );
        tags.add(new LeadSource()
                .setId("seminar")
                .setText("Seminar")
        );
        tags.add(new LeadSource()
                .setId("social")
                .setText("Social")
        );
        tags.add(new LeadSource()
                .setId("tradeshow")
                .setText("Tradeshow")
        );
        tags.add(new LeadSource()
                .setId("word_of_mouth")
                .setText("Word of mouth")
        );
        return tags;
    }
}
