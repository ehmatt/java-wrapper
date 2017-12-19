package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.Deal;
import com.onepagecrm.models.Note;
import com.onepagecrm.models.serializers.DateSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 29/08/2016.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class NoteFabricator extends BaseFabricator {

    public static Note single() {
        return new Note()
                .setId("57446f339007ba70dc20c13e")
                .setAuthor("Cillian M.")
                .setText("I rang Java to confirm his order. He confirmed an order for 16 solar panels for his store " +
                        "in Burlington. He amended his order for the store in Salem from 12 to 24 due to an " +
                        "unprecedented demand for eco products.")
                .setDate(DateSerializer.fromFormattedString("2016-05-24"))
                .setContactId("56fa81eb9007ba07fc000080")
                .setCreatedAt(DateSerializer.fromFormattedString("2016-05-24T15:11:47Z"))
                .setLinkedDealId("");
    }

    public static Note linked() {
        return linked(DealFabricator.single());
    }

    public static Note linked(Deal pLinkedDeal) {
        return new Note()
                .setId("571e036b9007ba4566062065")
                .setAuthor("Cillian M.")
                .setText(pLinkedDeal.getText() + "... NOTE")
                .setDate(pLinkedDeal.getExpectedCloseDate())
                .setContactId(pLinkedDeal.getContactId())
                .setCreatedAt(pLinkedDeal.getCreatedAt())
                .setLinkedDealId(pLinkedDeal.getId());
    }

    public static List<Note> list() {
        List<Note> notes = new ArrayList<>();
        notes.add(single());
        notes.add(linked());
        notes.add(new Note()
                .setId("57446f5d9007ba70dc20c142")
                .setAuthor("Cillian M.")
                .setText("Just got our first order from Java!! Details of the order to be confirmed. Reason we " +
                        "got it.. cause we were cheaper than the rest!!! I added in this to see if it gets " +
                        "updated and wrapped correctly.")
                .setDate(DateSerializer.fromFormattedString("2016-05-24"))
                .setContactId("56fa81eb9007ba07fc000080")
                .setCreatedAt(DateSerializer.fromFormattedString("2016-05-24T15:12:29Z"))
                .setLinkedDealId("")
        );
        return notes;
    }
}
