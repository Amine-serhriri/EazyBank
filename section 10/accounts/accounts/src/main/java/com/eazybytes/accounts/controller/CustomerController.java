package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = " REST API for Customers",
        description = " Rest APIs in EasyBank to FETCH customer details"
)
@RestController
@RequestMapping(path = "/api/",produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
public class CustomerController {
    private static  final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final ICustomerService iCustomerService;
    CustomerController(ICustomerService iCustomerService){
        this.iCustomerService =iCustomerService ;
    }
    @GetMapping(value = "fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto>fetchCustomerDetails(@RequestHeader("eazybank-correlation-id")String correlationId,
                                                                  @RequestParam
                                                                  @Pattern(regexp ="(^$|[0-9]{10})",message = "the mobile number should be 10 digits")
                                                                  String mobileNumber){
        logger.debug("easyBank-correlation-id found", correlationId);
        CustomerDetailsDto customerDetailsDto=iCustomerService.fetchCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDetailsDto);


    }
}
