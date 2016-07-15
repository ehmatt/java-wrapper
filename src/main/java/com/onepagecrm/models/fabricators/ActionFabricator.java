package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 16/02/2016.
 */
public class ActionFabricator extends BaseFabricator {

    public static Action single() {
        return new Action()
                .setId("5694e1999007ba687b0000ca")
                .setContactId("5694e1999007ba687b0000c9")
                .setText("Call Java")
                .setAssigneeId("559cd1866f6e656707000001")
                .setStatus(Action.Status.ASAP);
    }

    public static List<Action> list() {
        List<Action> actions = new ArrayList<>();
        actions.add(new Action()
                .setId("5694e1999007ba687b0000ca")
                .setContactId("5694e1999007ba687b0000c9")
                .setText("Call Java")
                .setAssigneeId("559cd1866f6e656707000001")
                .setStatus(Action.Status.ASAP));
        actions.add(new Action()
                .setId("5694e1959007ba687b000005")
                .setContactId("5694e1959007ba687b000004")
                .setText("Call Pawel")
                .setAssigneeId("559cd1866f6e656707000001")
                .setStatus(Action.Status.ASAP));
        actions.add(new Action()
                .setId("5694e1999007ba687b0000c1")
                .setContactId("5694e1999007ba687b0000c0")
                .setText("Call to follow up with Tiger")
                .setAssigneeId("559cd1866f6e656707000001")
                .setStatus(Action.Status.ASAP));
        return actions;
    }
}
