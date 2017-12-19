package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.CallResult;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class CallResultFabricator extends BaseFabricator {

    public static CallResult single() {
        return new CallResult()
                .setId("interested")
                .setDisplay("Interested");
    }

    public static List<CallResult> list() {
        List<CallResult> basics = new LinkedList<>();
        basics.add(new CallResult()
                .setPosition(0)
                .setId("interested")
                .setDisplay("Interested")
        );
        basics.add(new CallResult()
                .setPosition(1)
                .setId("not_interested")
                .setDisplay("Not interested")
        );
        basics.add(new CallResult()
                .setPosition(2)
                .setId("left_message")
                .setDisplay("Left message")
        );
        basics.add(new CallResult()
                .setPosition(3)
                .setId("no_answer")
                .setDisplay("No answer")
        );
        basics.add(new CallResult()
                .setPosition(4)
                .setId("other")
                .setDisplay("Other")
        );
        return basics;
    }
}
