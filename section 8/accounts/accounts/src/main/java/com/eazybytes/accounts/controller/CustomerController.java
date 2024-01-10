package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = " REST API for Customers",
        description = " Rest APIs in EasyBank to FETCH customer details"
)
@RestController
@RequestMapping(path = "/api/",produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
public class CustomerController {
    private final ICustomerService iCustomerService;
    CustomerController(ICustomerService iCustomerService){
        this.iCustomerService =iCustomerService ;
    }
    @GetMapping(value = "fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto>fetchCustomerDetails(@RequestParam
                                                                  @Pattern(regexp ="(^$|[0-9]{10})",message = "the mobile number should be 10 digits")
                                                                  String mobileNumber){
        CustomerDetailsDto customerDetailsDto=iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDetailsDto);


    }
}
