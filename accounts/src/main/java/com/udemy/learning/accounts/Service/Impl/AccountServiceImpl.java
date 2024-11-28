package com.udemy.learning.accounts.Service.Impl;


import com.udemy.learning.accounts.Constants.AccountConstants;
import com.udemy.learning.accounts.Dto.AccountDto;
import com.udemy.learning.accounts.Dto.CustomerDto;
import com.udemy.learning.accounts.Entity.Account;
import com.udemy.learning.accounts.Entity.Customer;
import com.udemy.learning.accounts.Exception.CustomerAlreadyExistsException;
import com.udemy.learning.accounts.Exception.ResourceNotFoundException;
import com.udemy.learning.accounts.Service.IAccountService;
import com.udemy.learning.accounts.mapper.AccountMapper;
import com.udemy.learning.accounts.mapper.CustomerMapper;
import com.udemy.learning.accounts.repository.AccountRepository;
import com.udemy.learning.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer Already Exists With the Mobile Number : " + customerDto.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDto , new Customer());
        customer.setCreatedBy("Anonymous");
        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        Account newAccount = createNewAccount(customer);
        accountRepository.save(newAccount);

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).
                orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).
                orElseThrow(() -> new ResourceNotFoundException("Account", "Customer",
                        customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account , new AccountDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated =  false;
        AccountDto accountDto = customerDto.getAccountDto();
        if(accountDto != null){
            Account account = accountRepository.findById(accountDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "Account Number",
                            accountDto.getAccountNumber().toString()));
            AccountMapper.mapToAccounts(accountDto,account);
            account = accountRepository.save(account);
            Long customerId = account.getCustomerId();

            Customer customer = customerRepository.findById(account.getCustomerId()).
                    orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Id", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }


    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedBy("Anonymous");
        newAccount.setCreatedAt(LocalDateTime.now());
        return newAccount;
    }
}
