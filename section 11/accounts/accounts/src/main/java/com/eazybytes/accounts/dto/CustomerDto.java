package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Schema(name="Customer",
        description = "Schema to hold customer and account information ")
@Data
public class CustomerDto {
    @NotEmpty(message =  "email can not be null or empty")
    @Email(message = "Email address should be a valid value ")
    private String email;

    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5,max = 30,message = "the length of the customer name should be between 5 and 30")
    private String name;

    @Pattern(regexp ="(^$|[0-9]{10})",message = "the mobile number should be 10 digits")
    private String mobileNumber;

    private AccountDto accountsDto;
}
