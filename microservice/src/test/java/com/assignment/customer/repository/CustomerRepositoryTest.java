package com.assignment.customer.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.customer.model.Customer;
import com.assignment.customer.model.PhoneState;

/**
 * 
 * @author ricardomorais
 * 
 *         This class represents the tests for the {@link CustomerRepository}
 *         and {@link CustomerCustomRepository} classes, using a database in
 *         memory H2.
 * 
 *         It is important to mention that {@link @DataJpaTest} provides some
 *         standard setup needed for testing the persistence layer: configuring
 *         H2, an in-memory database; setting Hibernate, Spring Data and the
 *         DataSource; performing an {link @EntityScan}; turning on SQL logging
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void givenCustomer_whenFindById_thenReturnCustomer() {
		// given
		Customer c1 = new Customer();
		c1.setId(1l);
		c1.setName("Customer c1");

		entityManager.persist(c1);
		entityManager.flush();

		// when
		Optional<Customer> found = customerRepository.findById(c1.getId());

		// then
		assertEquals(found.orElse(new Customer()).getId(), c1.getId());
	}

	@Test
	public void givenNoCustomer_whenFindById_thenReturnOptionalEmpty() {
		// given
		Long customerId = 1l;

		// when
		Optional<Customer> found = customerRepository.findById(customerId);

		// then
		assertThat(found.isPresent(), is(false));
	}

	@Test
	public void givenCustomers_whenFindAll_thenReturnAllCustomers() {
		// given
		Customer c1 = new Customer();
		c1.setId(1l);
		c1.setName("Customer c1");
		c1.setCountry("Uganda");
		c1.setPhone("(256) 123456789");

		Customer c2 = new Customer();
		c2.setId(2l);
		c2.setName("Customer c2");
		c2.setCountry("Uganda");
		c2.setPhone("(256) 985461233");

		entityManager.persist(c1);
		entityManager.persist(c2);
		entityManager.flush();

		// when
		List<Customer> customers = customerRepository.findAll();

		// then
		assertThat(customers, hasSize(2));
		assertThat(customers, containsInAnyOrder(c2, c1));
	}

	@Test
	public void givenNoCustomer_whenFindAll_thenReturnEmptyList() {
		// given - No Customer

		// when
		List<Customer> customers = customerRepository.findAll();

		// then
		assertThat(customers, is(empty()));
	}

	@Test
	public void givenCustomersWithinCountry_whenFindByCountry_thenReturnCustomers() {
		// given
		Customer c1 = new Customer();
		c1.setId(1l);
		c1.setName("Customer c1");
		c1.setCountry("Uganda");
		c1.setPhone("(256) 123456789");

		Customer c2 = new Customer();
		c2.setId(2l);
		c2.setName("Customer c2");
		c2.setCountry("Uganda");
		c2.setPhone("(256) 985461233");

		entityManager.persist(c1);
		entityManager.persist(c2);
		entityManager.flush();

		// when
		List<Customer> customers = customerRepository.findByCountry("Uganda");

		// then
		assertThat(customers, hasSize(2));
		assertThat(customers, containsInAnyOrder(c2, c1));
	}

	@Test
	public void givenCustomersWithinDifferentCountries_whenFindByCountry_thenReturnFilteredCustomers() {
		// given
		Customer c1 = new Customer();
		c1.setId(1l);
		c1.setName("Customer c1");
		c1.setCountry("Uganda");
		c1.setPhone("(256) 123456789");

		Customer c2 = new Customer();
		c2.setId(2l);
		c2.setName("Customer c2");
		c2.setCountry("Uganda");
		c2.setPhone("(256) 985461233");

		Customer c3 = new Customer();
		c3.setId(3l);
		c3.setName("Customer c3");
		c3.setCountry("Cameroon");
		c3.setPhone("(237) 28623632");

		entityManager.persist(c1);
		entityManager.persist(c2);
		entityManager.persist(c3);
		entityManager.flush();

		// when
		List<Customer> customers = customerRepository.findByCountry("Uganda");

		// then
		assertThat(customers, hasSize(2));
		assertThat(customers, containsInAnyOrder(c2, c1));
	}

	@Test
	public void givenNoCustomers_whenFindByCountry_thenReturnEmptyList() {
		// given No Customer

		// when
		List<Customer> customers = customerRepository.findByCountry("Uganda");

		// then
		assertThat(customers, is(empty()));
	}

	@Test
	public void givenCustomersWithinPhoneState_whenFindByPhoneState_thenReturnCustomers() {
		// given
		Customer c1 = new Customer();
		c1.setId(1l);
		c1.setName("Customer c1");
		c1.setCountry("Uganda");
		c1.setPhone("(256) 123456789");

		Customer c2 = new Customer();
		c2.setId(2l);
		c2.setName("Customer c2");
		c2.setCountry("Uganda");
		c2.setPhone("(256) 987654321");

		entityManager.persist(c1);
		entityManager.persist(c2);
		entityManager.flush();

		// when
		List<Customer> customers = customerRepository.findByPhoneState(PhoneState.VALID);

		// then
		assertThat(customers, hasSize(2));
		assertThat(customers, containsInAnyOrder(c2, c1));
	}

	@Test
	public void givenCustomersWithinDifferentPhoneStates_whenFindByPhoneState_thenReturnFilteredCustomers() {
		// given
		Customer c1 = new Customer();
		c1.setId(1l);
		c1.setName("Customer c1");
		c1.setCountry("Uganda");
		c1.setPhone("(256) 123456789");

		Customer c2 = new Customer();
		c2.setId(2l);
		c2.setName("Customer c2");
		c2.setCountry("Uganda"); // Invalid Phone Number
		c2.setPhone("(256) 12345");

		Customer c3 = new Customer();
		c3.setId(3l);
		c3.setName("Customer c3");
		c3.setCountry("Cameroon");
		c3.setPhone("(237) 28623632");

		entityManager.persist(c1);
		entityManager.persist(c2);
		entityManager.persist(c3);
		entityManager.flush();

		// when
		List<Customer> customers = customerRepository.findByPhoneState(PhoneState.VALID);

		// then
		assertThat(customers, hasSize(2));
		assertThat(customers, containsInAnyOrder(c3, c1));
	}

	@Test
	public void givenNoCustomers_whenFindByPhoneState_thenReturnEmptyList() {
		// given No Customer

		// when
		List<Customer> customers = customerRepository.findByPhoneState(PhoneState.INVALID);

		// then
		assertThat(customers, is(empty()));
	}

}
