package com.easybytes.loans.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Loans extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long loanId ;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoan ;

    private int amountPaid;

    private int outstandingAmount;
}
