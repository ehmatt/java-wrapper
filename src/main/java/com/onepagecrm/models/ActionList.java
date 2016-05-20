package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.models.serializers.ActionSerializer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 16/05/2016.
 */
public class ActionList extends ResourceList<Action> implements Serializable {

    private static final Logger LOG = Logger.getLogger(ActionList.class.getName());

    @Override
    public List<Action> nextPage(Map<String, Object> params) throws OnePageException {
        this.paginator.getNextPageNo();
        return null; // TODO - something here!!
    }

    @Override
    public List<Action> refresh(Map<String, Object> params) throws OnePageException {
        this.paginator = new Paginator();
        return null; // TODO - something here!!
    }

    public ActionList(List<Action> actions) {
        super(actions);
    }

    public ActionList() {
        this(null);
    }

    @Override
    public ActionList subList(int start, int end) {
        return new ActionList(super.subList(start, end));
    }

    @Override
    public String toString() {
        return ActionSerializer.toJsonArray(this.list);
    }
}
