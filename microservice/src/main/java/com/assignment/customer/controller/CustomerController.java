package com.assignment.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.customer.exception.ResourceNotFoundException;
import com.assignment.customer.model.Customer;
import com.assignment.customer.model.PhoneState;
import com.assignment.customer.repository.CustomerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author ricardomorais
 *
 *         This class represents the REST controller which exposes endpoints for
 *         customer-api.
 */

@RestController
@CrossOrigin
@RequestMapping("/v1")
@Api(value = "Customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@ApiOperation(value = "Get all customers.")
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@ApiOperation(value = "Get a person by its unique identifier.")
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getEmployeeById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));
		
		return ResponseEntity.ok().body(customer);
	}

	@ApiOperation(value = "Get all customers filtering by their country.")
	@GetMapping("/customers/search/findByCountry")
	public List<Customer> getAllCustomersByCountry(
			@ApiParam(value = "Country name", required = true, name = "name") @Param("name") String name) {
		return customerRepository.findByCountry(name);
	}

	@ApiOperation(value = "Get all customers filtering by their phone number state (VALID, INVALID or UNKWON).")
	@GetMapping("/customers/search/findByPhoneState")
	public List<Customer> getAllCustomersByPhoneState(
			@ApiParam(value = "Phone number state (VALID or INVALID)", required = true, name = "state") @Param("state") PhoneState state) {
		return customerRepository.findByPhoneState(state);
	}

}
