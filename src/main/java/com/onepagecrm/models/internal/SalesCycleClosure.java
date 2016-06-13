package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 13/06/2016.
 */
public class SalesCycleClosure implements Serializable {

    private String userId;
    private Date closedAt;
    private String comment;

    public SalesCycleClosure() {

    }

    public String getUserId() {
        return userId;
    }

    public SalesCycleClosure setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public SalesCycleClosure setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public SalesCycleClosure setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String toString() {
        return "SalesCycleClosure{" +
                "userId='" + userId + '\'' +
                ", closedAt=" + closedAt +
                ", comment='" + comment + '\'' +
                '}';
    }
}
