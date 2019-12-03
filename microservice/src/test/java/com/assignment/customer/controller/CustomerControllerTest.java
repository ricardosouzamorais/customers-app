package com.assignment.customer.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.customer.model.Customer;
import com.assignment.customer.repository.CustomerRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
public class CustomerControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerRepository customerRepository;

	@Test
	public void givenCustomers_whenGetCustomers_thenReturnJsonArray() throws Exception {
		// given
		Customer c1 = new Customer();
		c1.setId(1l);
		c1.setName("Customer c1");
		List<Customer> allCustomers = Arrays.asList(c1);

		when(customerRepository.findAll()).thenReturn(allCustomers);
		mvc.perform(get("/v1/customers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].name", is(c1.getName())));
	}

	@Test
	public void givenNoCustomer_whenGetCustomers_thenReturnJsonArray() throws Exception {
		// given
		when(customerRepository.findAll()).thenReturn(Collections.emptyList());

		mvc.perform(get("/v1/customers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

//	@Test
//	public void givenCustomer_whenGetCustomerById_thenReturnCustomer() throws Exception {
//		// given
//		Optional<Customer> customer = Optional.empty();
//		Long id = 1l;
//		Customer c1 = new Customer();
//		c1.setId(id);
//		c1.setName("Customer c1");
//
//		when(customerRepository.findById(id)).thenReturn(customer);
//		mvc.perform(get(String.format("/v1/customers/%d", id)).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
//				.andExpect(jsonPath("$[0].name", is(c1.getName())));
//	}
//
//	@Test
//	public void givenCustomer_whenGetCustomerById_thenReturnNotFound() throws Exception {
//		// given
//		when(customerRepository.findById(1l)).thenReturn(Optional.of(new Customer()));
//
//		mvc.perform(get(String.format("/v1/customers/%d", 1l)).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isNotFound());
//	}

}
