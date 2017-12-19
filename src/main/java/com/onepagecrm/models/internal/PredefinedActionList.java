package com.onepagecrm.models.internal;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.ResourceList;
import com.onepagecrm.models.serializers.PredefinedActionSerializer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 16/05/2016.
 */
public class PredefinedActionList extends ResourceList<PredefinedAction> implements Serializable {

    private static final Logger LOG = Logger.getLogger(PredefinedActionList.class.getName());

    @Override
    public List<PredefinedAction> nextPage(Map<String, Object> params) throws OnePageException {
        this.paginator.getNextPageNo();
        return null; // TODO - something here!!
    }

    @Override
    public List<PredefinedAction> refresh(Map<String, Object> params) throws OnePageException {
        this.paginator = new Paginator();
        return null; // TODO - something here!!
    }

    public PredefinedActionList(List<PredefinedAction> actions) {
        super(actions);
    }

    public PredefinedActionList() {
        this(null);
    }

    @Override
    public PredefinedActionList subList(int start, int end) {
        return new PredefinedActionList(super.subList(start, end));
    }

    @Override
    public String toString() {
        return PredefinedActionSerializer.toJsonString(this.list);
    }
}
