package com.onepagecrm.models.internal;

import java.io.Serializable;

/**
 * Created by mahasamatman on 26.05.16.
 */
public class CloseSalesCycle implements Serializable {
    private String comment;

    public CloseSalesCycle(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
