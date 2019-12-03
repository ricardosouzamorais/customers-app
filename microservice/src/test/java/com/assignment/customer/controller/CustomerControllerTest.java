package com.assignment.customer.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.assignment.customer.model.Customer;
import com.assignment.customer.model.PhoneState;
import com.assignment.customer.repository.CustomerRepository;

@RunWith(SpringRunner.class)
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
		
		mvc.perform(get("/v1/customers")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is(c1.getName())));
	}

	@Test
	public void givenNoCustomer_whenGetCustomers_thenReturnJsonArray() throws Exception {
		// given
		when(customerRepository.findAll()).thenReturn(Collections.emptyList());

		mvc.perform(get("/v1/customers")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void givenCustomer_whenGetCustomerById_thenReturnCustomer() throws Exception {
		// given
		Long id = 1l;
		Customer c1 = new Customer();
		c1.setId(id);
		c1.setName("Customer c1");

		when(customerRepository.findById(id)).thenReturn(Optional.of(c1));
		
		mvc.perform(get(String.format("/v1/customers/%d", id))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(c1.getName())));
	}

	@Test
	public void givenCustomer_whenGetCustomerById_thenReturnNotFound() throws Exception {
		// given
		when(customerRepository.findById(any(Long.class))).thenReturn(Optional.empty());

		mvc.perform(get(String.format("/v1/customers/%d", 1l))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void givenCustomers_whenFindByCountry_thenReturnJsonArray() throws Exception {
		// given
		String countryName = "Uganda";
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
		
		List<Customer> allCustomers = Arrays.asList(c1, c2);

		when(customerRepository.findByCountry(countryName)).thenReturn(allCustomers);
		
		mvc.perform(get(String.format("/v1/customers/search/findByCountry?name=%s", countryName))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is(c1.getName())))
				.andExpect(jsonPath("$[1].name", is(c2.getName())));
	}

	@Test
	public void givenNoCustomer_whenFindByCountry_thenReturnJsonArray() throws Exception {
		// given
		String countryName = "Morocco";
		
		when(customerRepository.findByCountry(countryName)).thenReturn(Collections.emptyList());

		mvc.perform(get(String.format("/v1/customers/search/findByCountry?name=%s", countryName))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	
	@Test
	public void givenCustomers_whenFindByPhoneNumberState_thenReturnJsonArray() throws Exception {
		// given
		PhoneState phoneState = PhoneState.VALID;
		
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
		
		List<Customer> allCustomers = Arrays.asList(c1, c2);

		when(customerRepository.findByPhoneState(phoneState)).thenReturn(allCustomers);
		
		mvc.perform(get(String.format("/v1/customers/search/findByPhoneState?state=%s", phoneState.name()))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is(c1.getName())))
				.andExpect(jsonPath("$[1].name", is(c2.getName())));
	}
	
	@Test
	public void givenNoCustomer_whenFindByPhoneNumberState_thenReturnJsonArray() throws Exception {
		// given
		PhoneState phoneState = PhoneState.VALID;
		
		when(customerRepository.findByPhoneState(phoneState)).thenReturn(Collections.emptyList());

		mvc.perform(get(String.format("/v1/customers/search/findByPhoneState?state=%s", phoneState.name()))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}
	
    @Test
    public void givenCustomersEndpoint_whenPost_thenNotAllowed() throws Exception {
        mvc.perform(post("/v1/customers")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenCustomersEndpoint_whenPut_thenNotAllowed() throws Exception {
        mvc.perform(put("/v1/customers")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenCustomersEndpoint_whenPatch_thenNotAllowed() throws Exception {
        mvc.perform(put("/v1/customers")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
    
    @Test
    public void givenCustomerEndpoint_whenPut_thenNotAllowed() throws Exception {

    	String putInJson = "{\"name\": \"Customer 1\", \"phone\":\"(1) 2689871232\", \"country\":\"USA\"}";

        mvc.perform(put(String.format("/v1/customers/%d", 1l))
                .content(putInJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenCustomer_whenPatch_thenNotAllowed() throws Exception {

    	String patchInJson = "{\"phone\":\"(55) 23456721\"}";

        mvc.perform(patch(String.format("/v1/customers/%d", 1l))
                .content(patchInJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
    
    @Test
    public void givenFindByCountryEndpoint_whenPost_thenNotAllowed() throws Exception {
        mvc.perform(post("/v1/customers/search/findByCountry")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenFindByCountryEndpoint_whenPut_thenNotAllowed() throws Exception {
        mvc.perform(put("/v1/customers/search/findByCountry")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenFindByCountryEndpoint_whenPatch_thenNotAllowed() throws Exception {
        mvc.perform(patch("/v1/customers/search/findByCountry")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenFindByPhoneStateEndpoint_whenPost_thenNotAllowed() throws Exception {
        mvc.perform(post("/v1/customers/search/findByPhoneState")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenFindByPhoneStateEndpoint_whenPut_thenNotAllowed() throws Exception {
        mvc.perform(put("/v1/customers/search/findByPhoneState")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenFindByPhoneStateEndpoint_whenPatch_thenNotAllowed() throws Exception {
        mvc.perform(patch("/v1/customers/search/findByPhoneState")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
    
}
