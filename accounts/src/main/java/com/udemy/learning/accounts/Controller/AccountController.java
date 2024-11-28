package com.udemy.learning.accounts.Controller;

import com.udemy.learning.accounts.Constants.AccountConstants;
import com.udemy.learning.accounts.Dto.AccountDto;
import com.udemy.learning.accounts.Dto.CustomerDto;
import com.udemy.learning.accounts.Dto.ResponseDto;
import com.udemy.learning.accounts.Service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private IAccountService accountService;
    public AccountController(IAccountService accountService){
        this.accountService = accountService;
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        accountService.createAccount(customerDto);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));

    }


    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);

    }


}
