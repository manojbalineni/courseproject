package com.udemy.learning.accounts.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account Information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the Customer",
            example = "Manoj Babu"
    )
    @NotEmpty(message = "Name Cannot be null or empty")
    @Size(min = 2 , max = 30 , message = "Name Should be between 2 and 30 characters")
    private String name;
    @Schema(
            description = "Email of the Customer",
            example = "manoj.balineni04@gmail.com"
    )
    @NotEmpty(message = "Email Cannot be null or empty")
    @Email(message = "Email must be valid")
    private String email;
    @Schema(
            description = "Mobile Number of the Customer",
            example = "9381439125"
    )
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Phone Number must be valid")
    private String mobileNumber;

    @Schema(
            description = "Account Details of the customer"
    )
    private AccountDto accountDto;
}
