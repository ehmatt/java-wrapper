package com.onepagecrm.models.fabricators;

import com.onepagecrm.models.internal.SalesCycleClosure;
import com.onepagecrm.models.serializers.DateSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 29/08/2016.
 */
public class SalesClosureFabricator extends BaseFabricator {

    public static SalesCycleClosure single() {
        return new SalesCycleClosure()
                .setUserId("559cd1866f6e656707000001")
                .setComment("My closing comment")
                .setClosedAt(DateSerializer.fromTimestamp("1462261876"));
    }

    public static List<SalesCycleClosure> list() {
        List<SalesCycleClosure> calls = new ArrayList<>();
        calls.add(single());
        calls.add(new SalesCycleClosure()
                .setUserId("561b937f9007ba4cc200004e")
                .setComment("John's comment")
                .setClosedAt(DateSerializer.fromTimestamp("1462261859"))
        );
        return calls;
    }
}
