package com.assignment.customer.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
		assertTrue(true);
		// given
		Customer c1 = new Customer();
		c1.setId(1l);
		c1.setName("Customer c1");
		List<Customer> allCustomers = Arrays.asList(c1);

		when(customerRepository.findAll()).thenReturn(allCustomers);

		mvc.perform(get("/api/customers")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is(c1.getName())));
	}

}
