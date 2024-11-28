package com.udemy.learning.accounts.Dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
