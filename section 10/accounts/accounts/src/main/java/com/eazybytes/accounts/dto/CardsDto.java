package com.eazybytes.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CardsDto {
    private String mobileNumber ;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed ;
    private int availableAmount ;
}
