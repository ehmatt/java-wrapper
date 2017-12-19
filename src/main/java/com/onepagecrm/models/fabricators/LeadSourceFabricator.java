package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.LeadSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
@SuppressWarnings("unused")
public class LeadSourceFabricator extends BaseFabricator {

    public static LeadSource single() {
        return new LeadSource()
                .setId("advertisement")
                .setText("Advertisement");
    }

    public static List<LeadSource> list() {
        List<LeadSource> leadSources = new ArrayList<>();
        leadSources.add(new LeadSource()
                .setId("advertisement")
                .setText("Advertisement")
        );
        leadSources.add(new LeadSource()
                .setId("email_web")
                .setText("Email or Web")
        );
        leadSources.add(new LeadSource()
                .setId("list_generation")
                .setText("List Generation")
        );
        leadSources.add(new LeadSource()
                .setId("other")
                .setText("Other")
        );
        leadSources.add(new LeadSource()
                .setId("referral")
                .setText("Referral")
        );
        leadSources.add(new LeadSource()
                .setId("seminar")
                .setText("Seminar")
        );
        leadSources.add(new LeadSource()
                .setId("social")
                .setText("Social")
        );
        leadSources.add(new LeadSource()
                .setId("tradeshow")
                .setText("Tradeshow")
        );
        leadSources.add(new LeadSource()
                .setId("word_of_mouth")
                .setText("Word of mouth")
        );
        return leadSources;
    }
}
