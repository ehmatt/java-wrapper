package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by mahasamatman on 13.07.16.
 */
public class CallList extends ResourceList<Call> implements Serializable {
	private String contactId;

	public CallList() {
		super(null);
	}

	public CallList(List<Call> list) {
		super(list);
	}

	public CallList(List<Call> list, String contactId) {
		super(list);
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String pContactId) {
		contactId = pContactId;
	}


	@Override
	public CallList nextPage(Map<String, Object> params) throws OnePageException {
		this.paginator.getNextPageNo();
		return Call.list(contactId, paginator);
	}

	@Override
	public CallList refresh(Map<String, Object> params) throws OnePageException {
		return null;
	}

}