package com.customer.account.demo.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.account.demo.constant.ValidationConstants;
import com.customer.account.demo.entity.Account;
import com.customer.account.demo.entity.Balance;
import com.customer.account.demo.entity.Customer;
import com.customer.account.demo.entity.ReceiptInfo;
import com.customer.account.demo.entity.Transaction;
import com.customer.account.demo.entity.TransactionHistory;
import com.customer.account.demo.model.CustomResponse;
import com.customer.account.demo.model.CustomerReciverDetails;
import com.customer.account.demo.model.PaymentConfirmRequest;
import com.customer.account.demo.model.PaymentConfirmResponse;
import com.customer.account.demo.model.ReceiptData;
import com.customer.account.demo.repository.AccountRepository;
import com.customer.account.demo.repository.BalanceRepository;
import com.customer.account.demo.repository.CustomerRepository;
import com.customer.account.demo.repository.ReceiptInfoRepository;
import com.customer.account.demo.repository.TransactionHistoryRepository;
import com.customer.account.demo.repository.TransactionRepository;
import com.customer.account.demo.service.PaymentService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    ReceiptInfoRepository receiptInfoRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BalanceRepository balanceRepository;
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseEntity<Object> confirmPayment(PaymentConfirmRequest request) {
    	//111111111
    	System.out.println("request :"+request.getCustomerId() +" mearchant Id:"+request.getMerchantId());
        try {
            Customer customer = customerRepository.findByCustomerId(request.getCustomerId());
            CustomResponse customResponse = new CustomResponse();

            if (customer == null) {
                customResponse.setStatusCode(404);
                customResponse.setStatus("Failure");
                customResponse.setMessage(ValidationConstants.CUSTOMER_NOT_FOUND);
                customResponse.setReasonCode("CUSTOMER_NOT_FOUND");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
            }

            Customer merchant = customerRepository.findByCustomerId(request.getMerchantId());
           
            if (merchant == null) {
                customResponse.setStatusCode(404);
                customResponse.setStatus("Failure");
                customResponse.setMessage(ValidationConstants.MERCHANT_NOT_FOUND);
                customResponse.setReasonCode("MERCHANT_NOT_FOUND");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
            }
//222222222222222
            System.out.println("customer :"+customer.getCustomerId() +" mearchant Id:"+merchant.getCustomerId());
            Account customerAccount = accountRepository.findByUserId(request.getCustomerId());
            System.out.println("customerAccount :"+customerAccount.getUserId());
            Account merchantAccount = accountRepository.findByUserId(request.getMerchantId());
            System.out.println("merchantAccount :"+merchantAccount.getUserId());
   //333333333333
            System.out.println("customerAccount :"+ customerAccount.getUserId() + "merchantAccount :"+ merchantAccount.getUserId());
            if (customerAccount.getOpeningBalance().compareTo(request.getAmount()) < 0) {
                CustomResponse response = new CustomResponse();
                response.setStatusCode(404);
                response.setStatus("failure");
                customResponse.setMessage(ValidationConstants.INSUFFICIENT_BALANCE);
                response.setReasonCode("INSUFFICIENT_BALANCE");
                return ResponseEntity.status(HttpStatus.FOUND).body(response);
            }else{
                Transaction transaction = saveTransaction(request, customerAccount, merchantAccount);

                System.out.println("transaction :"+transaction.getCreditCustomerId());
                PaymentConfirmResponse response = new PaymentConfirmResponse();

                response.setTransactionId(transaction.getTransactionId());

                CustomerReciverDetails customerReciverDetails = new CustomerReciverDetails();
                customerReciverDetails.setName(merchant.getCompanyName());
                customerReciverDetails.setContact(merchant.getMobileNo());
                response.setCustomerReciverDetails(customerReciverDetails);


                ReceiptInfo receiptInfo = saveReceiptInfo(request);

                ReceiptData receipt = new ReceiptData();
                receipt.setOrderID(receiptInfo.getOrderID());
                receipt.setOrderValue(receiptInfo.getOrderValue());
                receipt.setDiscountPercentage(receiptInfo.getDiscountPercentage());
                receipt.setDiscountValue(receiptInfo.getDiscountValue());
                receipt.setVatPercentage(receiptInfo.getVatPercentage());
                receipt.setVatValue(receiptInfo.getVatValue());
                response.setReceiptData(receipt);

                response.setPaymentMethod(request.getPaymentMethod());
                response.setPaymentDateTime(LocalDateTime.now());
                response.setCodeType(request.getCodeType());
                return ResponseEntity.status(HttpStatus.OK).body(response);

            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private Transaction saveTransaction(PaymentConfirmRequest request, Account customerAccount, Account merchantAccount) {
        String transactionId=generateUniqueTransactionNo();
        Transaction transaction= new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setDebitCustomerId(request.getCustomerId());
        transaction.setCreditCustomerId(request.getMerchantId());
        transaction.setStatus("Success");
        transaction.setInitiationTime(LocalDateTime.now());
        transaction.setLastUpdateTime(LocalDateTime.now());
        transaction.setTransactionType("Cr");
        transaction.setReasonType(request.getDesc());
        transaction.setAmount(request.getAmount());
        transaction.setDebitPartyCharge(BigDecimal.valueOf(0));
        transaction.setDebitPartyCommission(BigDecimal.valueOf(0));
        transaction.setCreditPartyCharge(BigDecimal.valueOf(0));
        transaction.setCreditPartyCommission(BigDecimal.valueOf(0));
        transaction.setFailureDescription("");
        transaction.setDebitParty(request.getCustomerName());
        transaction.setDebitPartyType(request.getCustomerType());
        transaction.setCreditParty(request.getMerchantName());
        transaction.setCreditPartyType(request.getMerchantType());
        transaction.setCostPrincipalCommission(BigDecimal.valueOf(0));
        transaction.setCostResidualCommission(BigDecimal.valueOf(0));
        transaction.setRevenueServiceFee(BigDecimal.valueOf(0));

        BigDecimal customerBalance=customerAccount.getOpeningBalance().subtract(request.getAmount());
        BigDecimal merchantBalance=merchantAccount.getOpeningBalance().add(request.getAmount());

        customerAccount.setOpeningBalance(customerBalance);
        customerAccount.setLastUpdated(LocalDateTime.now());
        accountRepository.save(customerAccount);

        merchantAccount.setOpeningBalance(merchantBalance);
        merchantAccount.setLastUpdated(LocalDateTime.now());
        accountRepository.save(merchantAccount);

        Balance balanceCustomer=new Balance();
        balanceCustomer.setTransactionId(transactionId);
        balanceCustomer.setAccountId(request.getCustomerId());
        balanceCustomer.setCustomerType(request.getCustomerType());
        balanceCustomer.setBalance(customerBalance);
        balanceCustomer.setTimeStamp(LocalDateTime.now());
        balanceRepository.save(balanceCustomer);

        Balance balanceMerchant=new Balance();
        balanceMerchant.setTransactionId(transactionId);
        balanceMerchant.setAccountId(request.getMerchantId());
        balanceMerchant.setCustomerType(request.getMerchantType());
        balanceMerchant.setBalance(merchantBalance);
        balanceMerchant.setTimeStamp(LocalDateTime.now());
        balanceRepository.save(balanceMerchant);

        updateTransactionHistory(request.getCustomerId(),request.getMerchantId(),request.getAmount(),request.getDesc());

        return  transactionRepository.save(transaction);
    }

    private void updateTransactionHistory(String customerId, String merchantId, BigDecimal amount, String desc) {
        TransactionHistory customerHistory=new TransactionHistory();
        customerHistory.setCustomerId(customerId);
        customerHistory.setAmount(amount);
        customerHistory.setDescription(desc);
        customerHistory.setUpdatedTime(LocalDateTime.now());
        customerHistory.setType("Debit");

        TransactionHistory merchantHistory=new TransactionHistory();
        merchantHistory.setCustomerId(merchantId);
        merchantHistory.setAmount(amount);
        merchantHistory.setDescription(desc);
        merchantHistory.setUpdatedTime(LocalDateTime.now());
        merchantHistory.setType("Credit");

        transactionHistoryRepository.save(customerHistory);
        transactionHistoryRepository.save(merchantHistory);
    }

    private ReceiptInfo saveReceiptInfo(PaymentConfirmRequest request){
        ReceiptInfo receiptInfo =new ReceiptInfo();
        receiptInfo.setOrderID(generateUniqueOrderId());
        receiptInfo.setOrderValue(request.getAmount());
        receiptInfo.setDiscountPercentage(BigDecimal.valueOf(0));
        receiptInfo.setDiscountValue(BigDecimal.valueOf(0));
        receiptInfo.setVatPercentage(BigDecimal.valueOf(0));
        receiptInfo.setVatValue(BigDecimal.valueOf(0));

        return receiptInfoRepository.save(receiptInfo);
    }
    private String generateUniqueTransactionNo(){
       String trId= transactionRepository.findMaxTransactionId();
        if(trId!=null){
            BigInteger tempId=new BigInteger(trId);
            tempId=tempId.add(BigInteger.valueOf(1));
            trId=tempId.toString();
        }else{
            trId="1234123412341234";
        }
        return  trId;
    }
    private String generateUniqueOrderId(){
        String orderId=receiptInfoRepository.findMaxOrderId();
        if(orderId!=null){
            BigInteger tempId=new BigInteger(orderId);
            tempId=tempId.add(BigInteger.valueOf(1));
            orderId=tempId.toString();
        }else{
            orderId="800000000001";
        }
        return  orderId;
    }


}


