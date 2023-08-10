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
import com.customer.account.demo.model.AccountRequest;
import com.customer.account.demo.service.AccountService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/accounts/v1.0")
@Validated
@Api(value = "/accounts", tags = { SwaggerConfig.ServiceTags.ACCOUNT_SERVICE })
public class AccountController implements AccountApi {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@Override
	@PostMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel,
			@Valid @RequestBody AccountRequest request) {
		return accountService.addAccount(request);
	}

	@Override
	@GetMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel) {
		return accountService.getAllAccount();
	}

	@Override
	@GetMapping(value = "/account/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel,@PathVariable String customerId) {
		return accountService.getAccount(customerId);
	}

	@Override
	@PutMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel, @Valid @RequestBody  AccountRequest request) {
		return accountService.updateAccount(request);
	}

	@Override
	@DeleteMapping(value = "/account/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteAccount(@RequestHeader(SURESH_CHANNEL) String sureshChannel,@PathVariable String customerId) {
		return accountService.deleteAccount(customerId);
	}

}
