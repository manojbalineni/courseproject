package com.udemy.learning.accounts.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Schema(
        name = "Account details of the Customer",
        description = "This contains the account details of the customer"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    @Schema(
            description = "Account number of the customer",
            example = "1234567890"

    )
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number must be valid")
    private Long accountNumber;
    @Schema(
            description = "Account type of the customer",
            example = "Savings"
    )
    @NotEmpty(message = "Account Type Cannot be null or non empty")
    private String accountType;
    @Schema(
            description = "Branch Address of the customer",
            name = "123 New York"

    )
    @NotEmpty(message = "Branch address cannot be null or non empty")
    private String branchAddress;
}
