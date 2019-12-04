package com.assignment.customer.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.assignment.customer.service.PhoneNumberStateStrategy;

/**
 * 
 * @author ricardomorais
 *
 *         This is the class that represents a Customer which has an
 *         international phone number, according to our model.
 */
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	// The max size of a integer in SQLite is 8 bytes and long fits this
	private long id;

	private String name;

	private String phone;

	private String country;

	@Transient
	private PhoneState phoneState;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Instead of changing the database and saving the state there, I decided to do
	 * this logic here since this entity bean will be transferred till front-end
	 * layer.
	 * 
	 * @return {link @PhoneState}
	 */
	public PhoneState getPhoneState() {
		return PhoneNumberStateStrategy.identifyPhoneState(this.country, this.phone);
	}

	public void setPhoneState(PhoneState phoneState) {
		this.phoneState = phoneState;
	}

	@Override
	public String toString() {
		return "Customer{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", country=" + country
				+ ", phoneState=" + getPhoneState() + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Customer)) {
			return false;
		}
		Customer customer = (Customer) o;
		return Objects.equals(name, customer.name) && Objects.equals(country, customer.country)
				&& Objects.equals(phone, customer.phone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, country, phone);
	}

}
