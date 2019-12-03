package com.assignment.customer.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.QueryHints;

import com.assignment.customer.model.Customer;
import com.assignment.customer.model.PhoneState;

/**
 * 
 * @author ricardomorais
 *
 *         This class works as an extension of CustomerRepository, implementing
 *         a custom method that searches the customers by {@link PhoneState}.
 */
public class CustomerCustomRepositoryImpl implements CustomerCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Customer> findByPhoneState(PhoneState phoneState) {
		try (Stream<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c", Customer.class)
				.setHint(QueryHints.HINT_FETCH_SIZE, 50).getResultList().stream()
				.filter(customer -> customer.getPhoneState() == phoneState)) {
			return customers.collect(Collectors.toList());
		}
	}

}
