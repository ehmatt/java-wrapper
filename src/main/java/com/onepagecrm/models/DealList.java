package com.onepagecrm.models;

import com.onepagecrm.models.serializer.DealSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles (cillian@onepagecrm.com) on 11/24/15.
 */
public class DealList extends ArrayList<Deal> implements Serializable {

    private List<Deal> deals;

    public DealList(List<Deal> deals) {
        this.deals = new ArrayList<>();
        if (deals != null && !deals.isEmpty()) {
            for (int i = 0; i < deals.size(); i++) {
                this.deals.add(deals.get(i));
            }
        }
    }

    public DealList() {
        this.deals = new ArrayList<>();
    }

    public String toString() {
        return DealSerializer.toJsonArray(this);
    }

    public boolean isEmpty() {
        return deals.isEmpty();
    }

    public int size() {
        return deals.size();
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = new ArrayList<>();
        if (deals != null && !deals.isEmpty()) {
            for (int i = 0; i < deals.size(); i++) {
                this.deals.add(deals.get(i));
            }
        }
    }

    public void add(int index, Deal deal) {
        deals.add(index, deal);
    }

    public Deal get(int index) {
        return deals.get(index);
    }

    // TODO : add rest of stuff for deals.
}
