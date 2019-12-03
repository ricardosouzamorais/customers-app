package com.assignment.customer.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ricardomorais
 *
 *         This enum represents the available countries and also has a property
 *         to define the correspondent regular expression to validate the phone
 *         number, which is used in the strategy.
 */

public enum Country {

	CAMEROON("^\\(237\\)\\ ?[2368]{7,8}$", "Cameroon"), 
	ETHIOPIA("^\\(251\\)\\ ?[1-59]{8}$", "Ethiopia"), 
	MOROCCO("^\\(212\\)\\ ?[5-9]{8}$", "Morocco"),
	MOZAMBIQUE("^\\(258\\)\\ ?[28]{7,8}$", "Mozambique"), 
	UGANDA("^\\(256\\)\\ ?\\d{9}$", "Uganda");

	static final Map<String, Country> altMap = new HashMap<String, Country>();

	static {
		for (Country country : Country.values()) {
			altMap.put(country.getCountryName(), country);
		}
	}

	private final String phoneRegExp;
	
	private final String countryName;

	private Country(final String phoneRegExp, final String countryName) {
		this.phoneRegExp = phoneRegExp;
		this.countryName = countryName;
	}

	public String getPhoneRegExp() {
		return phoneRegExp;
	}
	
	public String getCountryName() {
		return countryName;
	}
	
	public static Country lookup(String countryName) {
		return altMap.get(countryName);
	}
	
}
