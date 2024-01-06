package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name="Account",
        description = "Schema to hold  account information ")
public class AccountDto {
    @NotEmpty(message = "Account can not be a null empty")
    @Pattern(regexp ="(^$|[0-9]{10})",message = "the mobile number should be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type can not be null or empty ")
    private String accountType;

    @NotEmpty(message = "BranchAddress can not be null or empty ")
    private String branchAddress;
}
