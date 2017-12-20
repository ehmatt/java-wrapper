package com.onepagecrm.models.serializers;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.serializers.impl.SerializableResource;
import org.json.JSONObject;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 18/09/2016.
 */
public class AccountSerializer extends SerializableResource<Account> {

    private static volatile AccountSerializer instance;

    public static AccountSerializer getInstance() {
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
    protected Account singleResource() {
        return new Account();
    }

    @Override
    protected String singleTag() {
        return SerializableResource.ACCOUNT_TAG;
    }

    @Override
    protected String multipleTag() {
        return SerializableResource.ACCOUNTS_TAG;
    }

    @Override
    protected Account fromJsonObjectImpl(JSONObject accountObject) {
        return new Account();
    }

    @Override
    protected JSONObject toJsonObjectImpl(Account account) {
        return new JSONObject();
    }
}
