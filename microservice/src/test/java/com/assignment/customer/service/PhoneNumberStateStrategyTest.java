package com.assignment.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.assignment.customer.model.Country;
import com.assignment.customer.model.PhoneState;

@ExtendWith(SpringExtension.class)
public class PhoneNumberStateStrategyTest {

	@Test
	public void givenValidCameroonPhoneNumber_thenPhoneStateValid() {
		final String phoneNumber = "(237) 3286382";

		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.CAMEROON, phoneNumber), PhoneState.VALID);
	}

	@Test
	public void givenInvalidCameroonPhoneNumber_thenPhoneStateInvalid() {
		final String phoneNumber = "(237) 32856312";
		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.CAMEROON, phoneNumber), PhoneState.INVALID);
	}

	@Test
	public void givenValidEthiopiaPhoneNumber_thenPhoneStateValid() {
		final String phoneNumber = "(251) 12345912";
		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.ETHIOPIA, phoneNumber), PhoneState.VALID);
	}

	@Test
	public void givenInvalidEthiopiaPhoneNumber_thenPhoneStatInvalid() {
		final String phoneNumber = "(251) 25652132";
		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.ETHIOPIA, phoneNumber), PhoneState.INVALID);
	}

	@Test
	public void givenValidMoroccoPhoneNumber_thenPhoneStateValid() {
		final String phoneNumber = "(212) 96587868";
		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.MOROCCO, phoneNumber), PhoneState.VALID);
	}

	@Test
	public void givenInvalidMoroccoPhoneNumber_thenPhoneStateInvalid() {
		final String phoneNumber = "(212) 13453413";
		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.MOROCCO, phoneNumber), PhoneState.INVALID);
	}

	@Test
	public void givenValidMozambiquePhoneNumber_thenPhoneStateValid() {
		final String phoneNumber = "(258) 2828282";
		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.MOZAMBIQUE, phoneNumber), PhoneState.VALID);
	}

	@Test
	public void givenInvalidMozambiquePhoneNumber_thenPhoneStateInvalid() {
		final String phoneNumber = "(258) 28712828";
		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.MOZAMBIQUE, phoneNumber), PhoneState.INVALID);
	}

	@Test
	public void givenValidUgandaPhoneNumber_thenPhoneStateValid() {
		final String phoneNumber = "(256) 203762152";
		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.UGANDA, phoneNumber), PhoneState.VALID);
	}

	@Test
	public void givenInvalidUgandaPhoneNumber_thenPhoneStateInvalid() {
		final String phoneNumber = "(256) 123653";
		assertEquals(PhoneNumberStateStrategy.identifyPhoneState(Country.UGANDA, phoneNumber), PhoneState.INVALID);
	}

}
