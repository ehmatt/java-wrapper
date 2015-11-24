package com.onepagecrm.models;

import com.onepagecrm.models.serializer.DealSerializer;
import com.onepagecrm.net.ApiResource;

import java.io.Serializable;

public class Deal extends ApiResource implements Serializable {

    private String id;
    private String ownerId;
    // TODO : add rest of stuff for deals.

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Deal setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return DealSerializer.toJsonObject(this);
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Deal setOwnerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
