package com.udemy.learning.accounts.Controller;

import com.udemy.learning.accounts.Constants.AccountConstants;
import com.udemy.learning.accounts.Dto.AccountDto;
import com.udemy.learning.accounts.Dto.CustomerDto;
import com.udemy.learning.accounts.Dto.ErrorResponseDto;
import com.udemy.learning.accounts.Dto.ResponseDto;
import com.udemy.learning.accounts.Service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts EazyBank",
        description = "CRUD Rest APIs in EAZY Bank for Accounts"

)
@Validated
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private IAccountService accountService;
    public AccountController(IAccountService accountService){
        this.accountService = accountService;
    }

    @Operation(
            summary = "Create Account Rest API",
            description = "This is used to create a new account to users"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody @Valid CustomerDto customerDto){
        accountService.createAccount(customerDto);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));

    }

    @Operation(
            summary = "Fetch Account Details Rest API",
            description = "To fetch the account details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Phone Number must be valid")
                                                               String mobileNumber){
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);

    }
    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer & Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody @Valid CustomerDto customerDto){
        boolean accountUpdate = accountService.updateAccount(customerDto);
        if(accountUpdate){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));

        }
        else {
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                    body(new ResponseDto(AccountConstants.UPDATE_FAIL_CODE , AccountConstants.UPDATE_FAIL_MESSSAGE));
        }
    }

    @Operation(
            summary = "Delete Account Details REST API",
            description = "REST API to delete Customer & Account details based on a phone number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})",message = "Phone Number must be valid")String mobileNumber){
        boolean deleteAccount = accountService.deleteAccount(mobileNumber);
        if(deleteAccount){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));

        }
        else {
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                    body(new ResponseDto(AccountConstants.DELETE_FAIL_CODE , AccountConstants.DELETE_FAIL_MESSSAGE));
        }
    }


}
