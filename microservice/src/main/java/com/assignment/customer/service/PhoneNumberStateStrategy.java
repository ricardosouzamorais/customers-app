package com.assignment.customer.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.assignment.customer.model.Country;
import com.assignment.customer.model.PhoneState;

/**
 * 
 * @author ricardomorais
 *
 *         This interface has the logic to identify phone state based on each
 *         regex configured in the the {@link Country} enum.
 * 
 *         In case a new country is needed or a change at regular expression,
 *         just need to change the mentioned enum.
 */
public interface PhoneNumberStateStrategy {

	static PhoneState identifyPhoneState(Country country, String phoneNumber) {
		Pattern pattern = Pattern.compile(country.getPhoneRegExp());
		Matcher matcher = pattern.matcher(phoneNumber);
		if (matcher.matches()) {
			return PhoneState.VALID;
		}

		return PhoneState.INVALID;
	}

	static PhoneState identifyPhoneState(String countryName, String phoneNumber) {
		final Country country = Country.lookup(countryName);
		if (country == null) {
			return PhoneState.INVALID;

		} else {
			return identifyPhoneState(country, phoneNumber);
		}
	}
}
