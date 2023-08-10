package com.customer.account.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.account.demo.constant.ValidationConstants;
import com.customer.account.demo.entity.Customer;
import com.customer.account.demo.model.CustomResponse;
import com.customer.account.demo.model.CustomerResponse;
import com.customer.account.demo.repository.CustomerRepository;
import com.customer.account.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public ResponseEntity<Object> addCustomer(CustomerResponse request) {
		CustomResponse response;
		if(request.getCustomerId() !=null) {
			
			Customer customer=Customer.builder()
					.customerId(request.getCustomerId())
					.mobileNo(request.getMobileNo())
					.firstName(request.getFirstName())
					.lastName(request.getLastName())
					.fatherName(request.getFatherName())
					.grandFatherName(request.getGrandFatherName())
					.dateOfBirth(request.getDateOfBirth())
					.dateType(request.getDateType())
					.gender(request.getGender())
					.companyName(request.getCompanyName())
					.build();
			customerRepository.save(customer);
			response=CustomResponse.builder()
					.reasonCode("200")
					.status("Success")
					.message(ValidationConstants.CUSTOMER_SAVE_SUCCESSFULLY)
					.reasonCode("CUSTOMER_SAVE_SUCCESSFULLY")
					.build();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			response=CustomResponse.builder()
					.reasonCode("404")
					.status("Failure")
					.message(ValidationConstants.CUSTOMER_NOT_FOUND)
					.reasonCode("CUSTOMER_NOT_FOUND")
					.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
	}

	@Override
	public ResponseEntity<Object> getCustomer(String customerId) {
		CustomResponse response;
		Customer customer=customerRepository.getById(customerId);
		if(customer.getCustomerId() !=null) {
			CustomerResponse responses=CustomerResponse.builder()
					.customerId(customer.getCustomerId())
					.mobileNo(customer.getMobileNo())
					.firstName(customer.getFirstName())
					.lastName(customer.getLastName())
					.fatherName(customer.getFatherName())
					.grandFatherName(customer.getGrandFatherName())
					.dateOfBirth(customer.getDateOfBirth())
					.dateType(customer.getDateType())
					.gender(customer.getGender())
					.companyName(customer.getCompanyName())
					.build();
			return ResponseEntity.status(HttpStatus.OK).body(responses);
		}else {
			response=CustomResponse.builder()
					.reasonCode("404")
					.status("Failure")
					.message(ValidationConstants.CUSTOMER_NOT_FOUND)
					.reasonCode("CUSTOMER_NOT_FOUND")
					.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@Override
	public ResponseEntity<Object> updateCustomer(CustomerResponse request) {
		CustomResponse response;
		Customer customer=customerRepository.getById(request.getCustomerId());
		if(customer.getCustomerId() !=null) {
			
			customer.setCustomerId(request.getCustomerId());
			customer.setMobileNo(request.getMobileNo());
			customer.setFirstName(request.getFirstName());
			customer.setLastName(request.getLastName());
			customer.setFatherName(request.getFatherName());
			customer.setGrandFatherName(request.getGrandFatherName());
			customer.setDateOfBirth(request.getDateOfBirth());
			customer.setDateType(request.getDateType());
			customer.setGender(request.getGender());
			customer.setCompanyName(request.getCompanyName());
			customerRepository.save(customer);
			response=CustomResponse.builder()
					.reasonCode("200")
					.status("Success")
					.message(ValidationConstants.CUSTOMER_UPDATED_SUCCESSFULLY)
					.reasonCode("CUSTOMER_UPDATED_SUCCESSFULLY")
					.build();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		response=CustomResponse.builder()
				.reasonCode("404")
				.status("Failure")
				.message(ValidationConstants.CUSTOMER_NOT_FOUND)
				.reasonCode("CUSTOMER_NOT_FOUND")
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@Override
	public ResponseEntity<Object> getAllCustomer() {
		CustomResponse response;
		
		return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findAll());
	}

	@Override
	public ResponseEntity<Object> deleteCustomer(String customerId) {
		CustomResponse response;
		Customer customer=customerRepository.getById(customerId);
		if(customer.getCustomerId() !=null) {
			customerRepository.deleteById(customerId);
			response=CustomResponse.builder()
					.reasonCode("200")
					.status("Success")
					.message(ValidationConstants.CUSTOMER_DELETED_SUCCESSFULLY)
					.reasonCode("CUSTOMER_DELETED_SUCCESSFULLY")
					.build();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			response=CustomResponse.builder()
					.reasonCode("404")
					.status("Failure")
					.message(ValidationConstants.CUSTOMER_NOT_DELETED)
					.reasonCode("CUSTOMER_NOT_DELETED")
					.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

}
