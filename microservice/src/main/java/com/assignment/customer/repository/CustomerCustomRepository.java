package com.assignment.customer.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.assignment.customer.model.Customer;
import com.assignment.customer.model.PhoneState;

/**
 * 
 * @author ricardomorais
 * 
 *         This interface works as an interface for the custom customer data
 *         repository.
 */

public interface CustomerCustomRepository {

	List<Customer> findByPhoneState(@Param("state") PhoneState phoneState);

}
