package com.udemy.learning.accounts.Service;

import com.udemy.learning.accounts.Dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
}
