package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.serializers.impl.BaseSerializable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 18/09/2016.
 */
public class AccountSerializer extends BaseSerializable<Account> {

    private static Account DEFAULT_SINGLE = new Account();
    private static List<Account> DEFAULT_LIST = new ArrayList<>();

    private static volatile AccountSerializer instance;

    public static AccountSerializer getsInstance() {
        if (instance == null) {
            synchronized (AccountSerializer.class) {
                if (instance == null) {
                    instance = new AccountSerializer();
                }
            }
        }
        return instance;
    }

    // TODO - move stuff from UserSerializer here

    @Override
    protected Account defaultSingle() {
        return DEFAULT_SINGLE;
    }

    @Override
    protected List<Account> defaultList() {
        return DEFAULT_LIST;
    }

    @Override
    protected Account fromJsonObjectImpl(JSONObject baseResourceObject) {
        return null;
    }

    @Override
    protected List<Account> fromJsonArrayImpl(JSONArray baseResourceArray) {
        return null;
    }

    @Override
    protected JSONObject toJsonObjectImpl(Account baseResource) {
        return null;
    }

    @Override
    protected JSONArray toJsonArrayImpl(List<Account> baseResourceList) {
        return null;
    }
}
