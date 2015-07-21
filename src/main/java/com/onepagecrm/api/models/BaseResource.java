package com.onepagecrm.api.models;

import java.lang.reflect.Field;

public abstract class BaseResource {
		
	@Override 
	public String toString() {
		return String.format(
			"<%s@%s id=%s> JSON: %s",
			this.getClass().getName(),
			System.identityHashCode(this),
			this.getIdString(),
			this.toString());
	}

	private Object getIdString() {
		try {
			Field idField = this.getClass().getDeclaredField("id");
			return idField.get(this);
		} catch (SecurityException e) {
			return "";
		} catch (NoSuchFieldException e) {
			return "";
		} catch (IllegalArgumentException e) {
			return "";
		} catch (IllegalAccessException e) {
			return "";
		}
	}
	
	protected static boolean equals(Object a, Object b) {
		return a == null ? b == null : a.equals(b);
	}
}