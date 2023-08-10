package com.customer.account.demo.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.account.demo.constant.ValidationConstants;
import com.customer.account.demo.entity.Account;
import com.customer.account.demo.entity.Customer;
import com.customer.account.demo.model.AccountRequest;
import com.customer.account.demo.model.CustomResponse;
import com.customer.account.demo.model.CustomerResponse;
import com.customer.account.demo.model.GetAccountRequest;
import com.customer.account.demo.repository.AccountRepository;
import com.customer.account.demo.repository.CustomerRepository;
import com.customer.account.demo.service.AccountService;
import com.customer.account.demo.service.CustomerService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public ResponseEntity<Object> addAccount(AccountRequest request) {
		CustomResponse response;
		if(request.getUserId() !=null) {
			
			Account account=Account.builder()
					.userId(request.getUserId())
					.userType(request.getUserType())
					.openingBalance(request.getOpeningBalance())
					.lastUpdated(LocalDateTime.now())
					.status(request.getStatus())
					.branchName(request.getBranchName())
					.branchAddress(request.getBranchAddress())
					.build();
			accountRepository.save(account);
			response=CustomResponse.builder()
					.reasonCode("200")
					.status("Success")
					.message(ValidationConstants.ACCOUNT_SAVE_SUCCESSFULLY)
					.reasonCode("ACCOUNT_SAVE_SUCCESSFULLY")
					.build();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			response=CustomResponse.builder()
					.reasonCode("404")
					.status("Failure")
					.message(ValidationConstants.ACCOUNT_NOT_FOUND)
					.reasonCode("ACCOUNT_NOT_FOUND")
					.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
	}

	@Override
	public ResponseEntity<Object> getAccount(String customerId) {
		CustomResponse response;
		Account account=accountRepository.getById(customerId);
		if(account !=null) {
			GetAccountRequest responses=GetAccountRequest.builder()
					.userId(account.getUserId())
					.userType(account.getUserType())
					.openingBalance(account.getOpeningBalance())
					//.lastUpdated(account.getLastUpdated())
					.status(account.getStatus())
					.branchName(account.getBranchName())
					.branchAddress(account.getBranchAddress())
					.build();
			return ResponseEntity.status(HttpStatus.OK).body(responses);
		}else {
			response=CustomResponse.builder()
					.reasonCode("404")
					.status("Failure")
					.message(ValidationConstants.ACCOUNT_NOT_FOUND)
					.reasonCode("ACCOUNT_NOT_FOUND")
					.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@Override
	public ResponseEntity<Object> updateAccount(AccountRequest request) {
		CustomResponse response;
		Account account=accountRepository.getById(request.getUserId());
		if(account !=null) {
			
			account.setUserId(request.getUserId());
			account.setUserType(request.getUserType());
			account.setOpeningBalance(request.getOpeningBalance());
			account.setLastUpdated(LocalDateTime.now());
			account.setStatus(request.getStatus());
			account.setBranchName(request.getBranchName());
			account.setBranchAddress(request.getBranchAddress());
			
			accountRepository.save(account);
			response=CustomResponse.builder()
					.reasonCode("200")
					.status("Success")
					.message(ValidationConstants.ACCOUNT_UPDATED_SUCCESSFULLY)
					.reasonCode("ACCOUNT_UPDATED_SUCCESSFULLY")
					.build();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		response=CustomResponse.builder()
				.reasonCode("404")
				.status("Failure")
				.message(ValidationConstants.ACCOUNT_NOT_FOUND)
				.reasonCode("ACCOUNT_NOT_FOUND")
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@Override
	public ResponseEntity<Object> getAllAccount() {
		CustomResponse response;
		
		return ResponseEntity.status(HttpStatus.OK).body(accountRepository.findAll());
	}

	@Override
	public ResponseEntity<Object> deleteAccount(String customerId) {
		CustomResponse response;
		Account account=accountRepository.getById(customerId);
		if(account !=null) {
			accountRepository.deleteById(customerId);
			response=CustomResponse.builder()
					.reasonCode("200")
					.status("Success")
					.message(ValidationConstants.ACCOUNT_DELETED_SUCCESSFULLY)
					.reasonCode("ACCOUNT_DELETED_SUCCESSFULLY")
					.build();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			response=CustomResponse.builder()
					.reasonCode("404")
					.status("Failure")
					.message(ValidationConstants.ACCOUNT_NOT_DELETED)
					.reasonCode("ACCOUNT_NOT_DELETED")
					.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

}
