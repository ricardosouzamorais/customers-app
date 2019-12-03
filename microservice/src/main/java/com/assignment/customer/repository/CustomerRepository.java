package com.assignment.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.customer.model.Customer;

/**
 * 
 * @author ricardomorais
 *
 *         This interface is used by Spring to construct a JpaRepository for
 *         {@link Customer} with key being a {@link Long}.
 *         
 *         The methods specified here are implemented automatically by Spring.
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerCustomRepository {

	List<Customer> findByCountry(@Param("name") String name);

}