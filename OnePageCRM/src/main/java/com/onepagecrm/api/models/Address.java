package com.onepagecrm.api.models;


public class Address {

	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String countryCode;

	public Address(String address, String city, String state, String zipCode, String countryCode) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.countryCode = countryCode;
	}
	
	public Address() {
	
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}	
}
