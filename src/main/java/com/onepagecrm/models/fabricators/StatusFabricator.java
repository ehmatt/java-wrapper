package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
@SuppressWarnings("unused")
public class StatusFabricator extends BaseFabricator {

    public static Status single() {
        return new Status()
                .setId("559cd19f6f6e656707000005")
                .setColor("f96600")
                .setStatus("lead")
                .setText("Lead")
                .setDescription("You intend to sell to.");
    }

    public static List<Status> list() {
        List<Status> statuses = new ArrayList<>();
        statuses.add(new Status()
                .setId("559cd19f6f6e656707000005")
                .setColor("f96600")
                .setStatus("lead")
                .setText("Lead")
                .setDescription("You intend to sell to.")
        );
        statuses.add(new Status()
                .setId("559cd1a06f6e656707000006")
                .setColor("cc0000")
                .setStatus("prospect")
                .setText("Prospect")
                .setDescription("You are actively selling to.")
        );
        statuses.add(new Status()
                .setId("559cd1a06f6e656707000007")
                .setColor("3399ff")
                .setStatus("customer")
                .setText("Customer")
                .setDescription("You have had a transaction with.")
        );
        statuses.add(new Status()
                .setId("559cd1a06f6e656707000008")
                .setColor("666666")
                .setStatus("inactive")
                .setText("Inactive")
                .setDescription("You are not actively selling to.")
        );
        statuses.add(new Status()
                .setId("559cd1a06f6e656707000009")
                .setColor("000000")
                .setStatus("general")
                .setText("General")
                .setDescription("Non-sales related contacts.")
        );
        return statuses;
    }
}
