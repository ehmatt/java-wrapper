package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.CallResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 15/02/2016.
 */
public class CallResultFabricator extends BaseFabricator {

    public static CallResult single() {
        return new CallResult()
                .setId("interested")
                .setDisplay("Interested");
    }

    public static List<CallResult> list() {
        List<CallResult> basics = new ArrayList<>();
        basics.add(new CallResult()
                .setIntId(0)
                .setId("interested")
                .setDisplay("Interested")
        );
        basics.add(new CallResult()
                .setIntId(1)
                .setId("not_interested")
                .setDisplay("Not interested")
        );
        basics.add(new CallResult()
                .setIntId(2)
                .setId("left_message")
                .setDisplay("Left message")
        );
        basics.add(new CallResult()
                .setIntId(3)
                .setId("no_answer")
                .setDisplay("No answer")
        );
        basics.add(new CallResult()
                .setIntId(4)
                .setId("other")
                .setDisplay("Other")
        );
        return basics;
    }
}
