package com.customer.account.demo.controller;

import static com.customer.account.demo.constant.ApiHeaderConstants.SURESH_CHANNEL;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.account.demo.config.SwaggerConfig;
import com.customer.account.demo.model.CustomerResponse;
import com.customer.account.demo.service.CustomerService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/customers/v1.0")
@Validated
@Api(value = "/wallets", tags = { SwaggerConfig.ServiceTags.CUSTOMER_SERVICE })
public class CustomerController implements CustomerApi {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@Override
	@PostMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addCustomer(@RequestHeader(SURESH_CHANNEL) String sureshChannel,
			@Valid @RequestBody CustomerResponse request) {
		return customerService.addCustomer(request);
	}

	@Override
	@GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllCustomer(@RequestHeader(SURESH_CHANNEL) String sureshChannel) {
		return customerService.getAllCustomer();
	}

	@Override
	@GetMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCustomer(@RequestHeader(SURESH_CHANNEL) String sureshChannel,@PathVariable String customerId) {
		return customerService.getCustomer(customerId);
	}

	@Override
	@PutMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCustomer(@RequestHeader(SURESH_CHANNEL) String sureshChannel, @Valid @RequestBody  CustomerResponse request) {
		return customerService.updateCustomer(request);
	}

	@Override
	@DeleteMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteCustomer(@RequestHeader(SURESH_CHANNEL) String sureshChannel,@PathVariable String customerId) {
		return customerService.deleteCustomer(customerId);
	}

}
